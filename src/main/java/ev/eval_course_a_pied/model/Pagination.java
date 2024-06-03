package ev.eval_course_a_pied.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Pagination {

    Integer pageNumber;
    Integer pageSize;
    Integer totalPages;
    Integer TotalContent;
    boolean hasPrevious;
    boolean hasNext;
}
