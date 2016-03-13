package de.laboranowitsch.jndimulti.controller;

import de.laboranowitsch.jndimulti.entity.Item;
import de.laboranowitsch.jndimulti.entity.People;
import de.laboranowitsch.jndimulti.repo.ItemRepo;
import de.laboranowitsch.jndimulti.repo.PeopleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Echo Controller to have something to work on Tomcat server.
 *
 * Created by cla on 3/10/16.
 */
@RestController
@RequestMapping("/api")
public class EchoController {

    private static Logger LOG = LoggerFactory.getLogger(EchoController.class);

    @Autowired
    private PeopleRepo peopleRepo;
    @Autowired
    private ItemRepo itemRepo;

    @RequestMapping(value = "/echo/{input}", method = RequestMethod.GET)
    public ResponseEntity<PeopleItemResponse> echoInput(@PathVariable(value = "input") final String input) {
        LOG.info("Received input: {}", input);
        Integer count = itemRepo.insertItem(input);
        LOG.info("Count: {}", count);
        List<People> peoples = peopleRepo.collectPeople();
        List<Item> items = itemRepo.collectItems(input);
        return new ResponseEntity<>(new PeopleItemResponse(peoples, items), HttpStatus.OK);
    }
}
