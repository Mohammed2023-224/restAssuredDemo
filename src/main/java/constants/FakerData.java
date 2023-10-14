package constants;
import com.github.javafaker.Faker;
public class FakerData {
	
	
//	generating fake data
	Faker faker = new Faker();

	public  String fname=faker.name().firstName();
	public int age=faker.number().randomDigit();
	public String lname=faker.name().lastName();
	public String address=faker.address().fullAddress();
	

}
