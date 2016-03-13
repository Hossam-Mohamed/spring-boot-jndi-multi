package de.laboranowitsch.jndimulti;

import de.laboranowitsch.jndimulti.entity.Item;
import de.laboranowitsch.jndimulti.entity.People;
import de.laboranowitsch.jndimulti.repo.ItemRepo;
import de.laboranowitsch.jndimulti.repo.PeopleRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MultiJndiApplication.class, IntTestConfiguration.class})
@WebAppConfiguration
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/create-tables.sql", "classpath:sql/insert-data.sql"})
})
/**
 * Integration Tests for Item and People Repositories
 * {@link de.laboranowitsch.jndimulti.repo.ItemRepo}
 * {@link de.laboranowitsch.jndimulti.repo.PeopleRepo}
 *
 * Test is going to be started with environment: -Dspring.profiles.active=int-test
 *
 * Created by cla on 3/10/16.
 */
public class MultiJndiApplicationTests {

	@Autowired
	private ItemRepo itemRepo;
	@Autowired
	private PeopleRepo peopleRepo;

	/**
	 * Test that context is properly build
	 */
	@Test
	public void contextLoads() {
		assertThat("itemRepo is present", itemRepo, is(notNullValue()));
		assertThat("peopleRepo is present", peopleRepo, is(notNullValue()));
	}

	/**
	 * Test that people data is read properly
	 */
	@Test
	public void testReadPeopleRepo() {
		List<People> peoples = peopleRepo.collectPeople();
		System.out.println(peoples);
		assertThat("5 elements retrieved", peoples.size(), is(equalTo(5)));
		assertThat("contains JILL one times", peoples.stream().filter(elem -> elem.getFirstName().equals("JILL")).count(), is(equalTo(1L)));
		assertThat("contains JOE one times", peoples.stream().filter(elem -> elem.getFirstName().equals("JOE")).count(), is(equalTo(1L)));
		assertThat("contains JUSTIN one times", peoples.stream().filter(elem -> elem.getFirstName().equals("JUSTIN")).count(), is(equalTo(1L)));
		assertThat("contains JANE one times", peoples.stream().filter(elem -> elem.getFirstName().equals("JANE")).count(), is(equalTo(1L)));
		assertThat("contains JOHN one times", peoples.stream().filter(elem -> elem.getFirstName().equals("JOHN")).count(), is(equalTo(1L)));
		assertThat("contains DOE 5 times", peoples.stream().filter(elem -> elem.getLastName().equals("DOE")).count(), is(equalTo(5L)));
	}

	/**
	 * Test that item data is read properly
	 */
	@Test
	public void testReadItemRepo() {
		List<Item> items = itemRepo.collectItems("hello");
		assertThat("1 element retrieved", items.size(), is(equalTo(1)));
		assertThat("contains hello one times", items.stream().filter(elem -> elem.getItem().equals("hello")).count(), is(equalTo(1L)));
	}

	/**
	 * Test that item data is written properly
	 */
	@Test
	public void testSaveItemRepo() {
		Integer updateCount = itemRepo.insertItem("hello2");
		assertThat("updateCount is 1", updateCount, is(equalTo(1)));
		List<Item> items = itemRepo.collectItems("hello2");
		assertThat("1 element retrieved", items.size(), is(equalTo(1)));
		assertThat("contains hello 2one times", items.stream().filter(elem -> elem.getItem().equals("hello2")).count(), is(equalTo(1L)));
	}

}
