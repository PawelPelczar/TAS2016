package pl.amu.service;

import jersey.repackaged.com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.amu.service.rest.dao.User;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UsersApplication.class)
@WebIntegrationTest("server.port:9000")
public class UsersApplicationTests {

	private WebTarget target = ClientBuilder.newClient().target("http://localhost:9000");

	@Test
	public void shouldReturnHelloMsg() {
		//when
		String responseData = target.path("hello/Mr. White").request().get(String.class);

		//then
		assertEquals("Hello response", "hello: Mr. White", responseData);
	}

	@Test
	public void shouldSaveUser() {
		//given
		User user = new User("Mr. White", "chemik", "chemik@x.com", Lists.newArrayList());

		//when
		Response saveUserResponse = target.path("users").request().post(Entity.json(user));

		//then
		assertEquals("Status zapisu usera", saveUserResponse.getStatus(), 200);
		saveUserResponse.close();

		//clean
		target.path("users/chemik").request().delete();
	}

	@Test
	public void shouldGetUserByLogin() {
		//given
		User user = new User("Mr. White", "chemik", "chemik@x.com", Lists.newArrayList());
		target.path("users").request().post(Entity.json(user));

		//when
		User returnedUser = target.path("users/chemik").request().get(User.class);

		//then
		assertEquals("Pobranie usera 'chemik': ", returnedUser, user);

		//clean
		target.path("users/chemik").request().delete();
	}

	@Test
	public void shouldListUsers() {
		//given
		User u = new User("Lord Wader", "lordWader", "lord@darkstar.com", Lists.newArrayList());
		target.path("users").request().post(Entity.json(u));

		//when
			List<User> users = target.path("users").request().get(
				new GenericType<List<User>>() {}
			);

		//then
		assertEquals("Pobrana lista userow: ", users.size(), 1);
		assertEquals("Pobrana lista userow: ", users.get(0).getName(), "Lord Wader");

		//clean
		target.path("users/lordWader").request().delete();
	}

}
