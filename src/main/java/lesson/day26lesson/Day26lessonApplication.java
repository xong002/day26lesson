package lesson.day26lesson;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import lesson.day26lesson.repository.ShowsRepository;

@SpringBootApplication
public class Day26lessonApplication implements CommandLineRunner {

	@Autowired
	private ShowsRepository showRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day26lessonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JsonObject json = Json.createObjectBuilder()
							.add("name","fred")
							.add("hobbies", 
								Json.createArrayBuilder()
									.add("music")
									.add("jog"))
							.build();
		
		System.out.println(json.toString());
		
		// for (Document d : showRepo.getShowsByType("Talk Show", 3, 0)) {
		// 	System.out.println(d);
		// }

		List<String> allTypes = showRepo.getAllTypes();
		System.out.println(allTypes.get(0));

		// for (String r : showRepo.findShowsByGenre("Anime", "Horror")) {
		// 	System.out.println(r);
		// }

		// List<Document> docs = showRepo.findShowsByName("Bitten");
		// for (Document d : docs){
		// String name = d.getString("name");
		// String type = d.getString("type");
		// Integer runtime = d.getInteger("runtime");
		// System.out.printf("name: %s\ntype: %s\nruntime: %d\n", name, type, runtime);

		// List<String> genres = d.getList("genres", String.class);
		// System.out.printf("\tgenres: %s\n", genres);

		// Double averageRating = d.get("rating",Document.class).getDouble("average");
		// System.out.printf("\taverage rating: %.2f\n", averageRating);

		// }
	}

}
