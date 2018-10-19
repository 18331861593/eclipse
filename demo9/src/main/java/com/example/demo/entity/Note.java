package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
//@NamedQuery(name = "Note.findByTitle", query = "select p from Note p where p.title = ?1")

@NamedQueries({@NamedQuery( name = "Note.findByTitle", query = "select p from Note p where p.title = ?1" ),
		@NamedQuery  (name= "Note.withTitleAndBodyQuery", query = "select p from Note p where p.title=?1 and body=?2")}
)
public class Note {
	
	
	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", body=" + body + ", tags=" + tags + "]";
	}

	@Id
	/*@SequenceGenerator(name="note_generator", sequenceName="note_sequence", initialValue = 5)
	@GeneratedValue(generator = "note_generator")*/
	private long id;
	
	private String title;
	
	private String body;
	
	@ManyToMany
	private List<Tag> tags;
	
	protected Note() {};
	
	public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

	
	
	
}
