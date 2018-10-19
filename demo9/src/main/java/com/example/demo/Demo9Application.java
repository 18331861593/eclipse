package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.Note;
import com.example.demo.repository.NoteRepository;

@SpringBootApplication
public class Demo9Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo9Application.class, args);
	}

	/*@Bean
	public CommandLineRunner demo(final NoteRepository repository){
		return new CommandLineRunner(){
			public void run(String...args) throws Exception{
				repository.save(new Note("Have a nice Day","Don’t let mental blocks control you. Set yourself free. Confront your fear and turn the mental blocks into building blocks."));
				repository.save(new Note("x-Notes", "Chloe O'Brian,Kim Bauer"));
	            repository.save(new Note("Some Others", "No news is good news"));
	                
	            System.out.println("Notes found with findAll():");
	            System.out.println("-------------------------------");
                for (Note note : repository.findAll()) {
                	System.out.println(note.toString());
                }    
                
                
                Note note = repository.findOneById(1L);
                System.out.println("Note found with findOneById(1L):");
                System.out.println("--------------------------------");
                System.out.println(note.toString());
			}
		};
	}*/

}
