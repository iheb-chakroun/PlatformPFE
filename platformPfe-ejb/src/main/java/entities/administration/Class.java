package entities.administration;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import entities.users.Student;

@Entity
public class Class {
	
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String label;
		private String scholarYear;

		@JsonIgnoreProperties("classes")
		@ManyToOne
		private Option option;

		@JsonIgnoreProperties("classe")
		@OneToMany(mappedBy = "classe")
		private List<Student> students;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getScholarYear() {
			return scholarYear;
		}

		public void setScholarYear(String scholarYear) {
			this.scholarYear = scholarYear;
		}

		public Option getOption() {
			return option;
		}

		public void setOption(Option option) {
			this.option = option;
		}

		public List<Student> getStudents() {
			return students;
		}

		public void setStudents(List<Student> students) {
			this.students = students;
		}

}
