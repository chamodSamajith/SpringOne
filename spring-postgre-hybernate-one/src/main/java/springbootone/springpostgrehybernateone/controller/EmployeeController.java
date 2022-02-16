package springbootone.springpostgrehybernateone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootone.springpostgrehybernateone.dto.EmployeeDTO;
import springbootone.springpostgrehybernateone.exception.ResourceNotFoundException;
import springbootone.springpostgrehybernateone.model.Employee;
import springbootone.springpostgrehybernateone.service.EmployeeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //get employees
    @GetMapping("/")
    public List<EmployeeDTO> getAllEmployee() {
        return this.employeeService.getAllEmployees();
    }

    //get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId){
        try {
            return employeeService.getEmployeeById(employeeId);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //save employee
    @PostMapping("/save")
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        return this.employeeService.saveEmployeeData(employeeDTO);
    }

    //update employee    - url id parameter will bind to the Long employeeId variable
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Employee employeeDetails){
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//
//        employee.setEmail(employeeDetails.getEmail());
//        employee.setLastName(employeeDetails.getLastName());
//        employee.setFirstName(employeeDetails.getFirstName());
//        return ResponseEntity.ok(this.employeeRepository.save(employee));
        try {
            return employeeService.updateEmployee(employeeId,employeeDetails);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //delete employee
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//
//        this.employeeRepository.delete(employee);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
        return employeeService.removeEmployee(employeeId);
    }
}
