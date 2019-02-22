package lab05;

public class StudentRecord{

	private String studentID;
	private float midterm;
	private float assignments;
	private float finalExam;
	private double finalMark;
	private char letterGrade;
	
	StudentRecord(){}
	
	StudentRecord(String id, float m, float a, float f){
		studentID = id;
		midterm = m;
		assignments = a;
		finalExam = f;
	}
	
	double setFinal(double m, double a, double f) {
		return (0.2*m) + (0.3*a) + (0.5*f);
	}
	
	public char assignLetter(double grade) {
		if(grade < 50) {
			return 'F';
		}
		else if(grade < 60) {
			return 'D';
		}
		else if(grade < 70) {
			return 'C';
		}
		else if(grade < 80) {
			return 'B';
		}
		else {
			return 'A';
		}
	}
	
	public String getID() {
		return studentID;
	}
	
	public float getAsmt() {
		return assignments;
	}
	
	public float getMid() {
		return midterm;
	}
	
	public float getFinal() {
		return finalExam;
	}
	
	public double getMark() {
		finalMark = setFinal(midterm,assignments,finalExam);
		return finalMark;
	}
	
	public char getGrade() {
		letterGrade = assignLetter(finalMark);
		return letterGrade;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
