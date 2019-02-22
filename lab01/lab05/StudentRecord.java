package lab05;

public class StudentRecord {
	int StudentID;
	double midterm;
	double assignments;
	double finalExam;
	double finalMark = (0.2*assignments) + (0.3*midterm) + (0.5*finalExam);
	char letterGrade = setLetterGrade();

	char setLetterGrade() {
		if(finalMark < 80) {
			if(finalMark < 70) {
				if(finalMark < 60) {
					if(finalMark < 50) {
						return 'F';
					}
					return 'D';
				}
				return 'C';
			}
			return 'B';
		}
		else {
			return 'A';
		}
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
