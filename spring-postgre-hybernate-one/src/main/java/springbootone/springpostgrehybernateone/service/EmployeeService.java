package springbootone.springpostgrehybernateone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import springbootone.springpostgrehybernateone.dto.EmployeeDTO;
import springbootone.springpostgrehybernateone.exception.ResourceNotFoundException;
import springbootone.springpostgrehybernateone.model.Employee;
import springbootone.springpostgrehybernateone.repositoty.EmployeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //save employee
    public EmployeeDTO saveEmployeeData(EmployeeDTO employeeDTO) {
        employeeRepository.save(new Employee(
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail()
        ));
        return employeeDTO;
    }

    //get all employees
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
        return employeeList.stream().map(employee -> new EmployeeDTO(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        )).collect(Collectors.toList());
    }
    //get Employee by ID

    //    public EmployeeDTO getEmployeeById(Long employeeId){
//        Employee employee = employeeRepository.findById(employeeId).get();
//        return new EmployeeDTO(
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail()
//        );
//    }
    public ResponseEntity<Employee> getEmployeeById(Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found, Id is: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    //Update employee
    public ResponseEntity<Employee> updateEmployee(Long employeeId,
                                                   Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmail(employeeDetails.getEmail());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());

        return ResponseEntity.ok(this.employeeRepository.save(employee));
    }

    //Remove Employee
    public Map<String, Boolean> removeEmployee(Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
