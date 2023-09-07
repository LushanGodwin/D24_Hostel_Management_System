package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO studentDto);

    List<StudentDTO> getAllStudent();

    boolean updateStudent(StudentDTO studentDTO);

    boolean deleteStudent(String studentId);

    String generatenextStudentId();

    StudentDTO searchStudent(String studentId);
}
