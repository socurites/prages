package com.melon.helloes.runner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.melon.helloes.domain.SongDoc;

/**
 * 컨텐츠 크롤링 서비스
 * 
 * @author socurites
 *
 */
public class MCrawlerBulkRunner {
	private static final String INPUT_FILE_PATH = "helloes/dummy/song_indexing_target.txt";
	private static final String SONG_DETAIL_URL = "http://www.melon.com/song/detail.json?songId=";
	private static final String OUTPUT_FILE_PATH = "/home/socurites/Desktop/result4.txt";

	public static void main(String[] args) throws InterruptedException {
		MCrawlerBulkRunner runner = new MCrawlerBulkRunner();
		runner.run();
	}

	private void run() throws InterruptedException {
		MCrawler crawler = new MCrawler();

		ClassLoader cl = getClass().getClassLoader();
		File file = new File(cl.getResource(INPUT_FILE_PATH).getFile());

		try (Scanner scanner = new Scanner(file)) {
			PrintWriter pw = new PrintWriter(new FileOutputStream(new File(OUTPUT_FILE_PATH)), true);

			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();

				String[] tokens = line.split(" ");

				SongDoc doc = crawler.crawlSongDetail(SONG_DETAIL_URL + tokens[1]);

				if (doc != null) {
					System.out.println(tokens[0]);
					pw.println("{ \"index\" : { \"_index\" : \"song\", \"_type\" : \"detail\", \"_id\" : \""
							+ doc.getSongId() + "\" }}");
					pw.println("{" + doc.formatStringForBulk() + "}");

					Thread.sleep(1000);
				}

			}

			scanner.close();
			pw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// curl -s -XPOST 'http://localhost:9200/song/_bulk' --data-binary @song.data.json
	}
}
