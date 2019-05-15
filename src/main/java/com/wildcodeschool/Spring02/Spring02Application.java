package com.wildcodeschool.Spring02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Controller
@SpringBootApplication
public class Spring02Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring02Application.class, args);
	}

	@RequestMapping("/doctor/{id}")
	@ResponseBody
	Doctor getDoctor(@PathVariable int id, @RequestParam (required = false) boolean details) {

			Doctor christopher = new Doctor("9", "Christopher");
			Doctor david = new Doctor("10", "David");
			Doctor matt = new Doctor("11", "Matt");
			Doctor peter = new Doctor("12", "Peter");
			Doctor jodie = new Doctor("13", "Jodie");

			ArrayList<Doctor> doctorList = new ArrayList<>();
			doctorList.add(christopher);
			doctorList.add(david);
			doctorList.add(matt);
			doctorList.add(peter);
			doctorList.add(jodie);

			ExtendedDoctor extendChristopher = new ExtendedDoctor("9", "Christopher", "13","41");
			ExtendedDoctor extendDavid = new ExtendedDoctor("10", "David", "47","34");
			ExtendedDoctor extendMatt = new ExtendedDoctor("11", "Matt", "44","27");
			ExtendedDoctor extendPeter = new ExtendedDoctor("12", "Peter", "40","55");
			ExtendedDoctor extendJodie = new ExtendedDoctor("13", "Jodie", "11","35");

			ArrayList<ExtendedDoctor> extendDoctorsList = new ArrayList<>();
			extendDoctorsList.add(extendChristopher);
			extendDoctorsList.add(extendDavid);
			extendDoctorsList.add(extendMatt);
			extendDoctorsList.add(extendPeter);
			extendDoctorsList.add(extendJodie);

		if ( (id >= 14) || (id <= 0)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible de récupérer l'incarnation " + id);
		}
		if ( (id == 1) || (id == 2) || (id == 3) || (id == 4) || (id == 5) || (id == 6) || (id == 7) || (id == 8)) {
			throw new ResponseStatusException(HttpStatus.SEE_OTHER, "See Other");
		}
		if (details){
			return extendDoctorsList.get(id - 9);
		}

		return doctorList.get(id - 9);

	}

	class Doctor {
		private String name;
		private String number;

		public Doctor(String name, String number) {
			this.name = name;
			this.number = number;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getName() {
			return name;
		}

		public String getNumber() {
			return number;
		}
	}

	class ExtendedDoctor extends Doctor {

		private String numberOfEpisodes;
		private String ageAtStart;

		public ExtendedDoctor(String name, String number, String numberOfEpisodes, String ageAtStart) {
			super(name, number);
			this.ageAtStart = ageAtStart;
			this.numberOfEpisodes = numberOfEpisodes;
		}

		public String getNumberOfEpisodes() {
			return numberOfEpisodes;
		}

		public String getAgeAtStart() {
			return ageAtStart;
		}

		public void setNumberOfEpisodes(String numberOfEpisodes) {
			this.numberOfEpisodes = numberOfEpisodes;
		}

		public void setAgeAtStart(String ageAtStart) {
			this.ageAtStart = ageAtStart;
		}
	}

}
