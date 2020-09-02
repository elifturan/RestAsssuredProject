package Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Jobs {
    private String job_id;
    private String job_title;
    private int min_salary;
    private int max_salary;
    private List<Map<String ,Object>> links;

  public Jobs(){

  }
}
