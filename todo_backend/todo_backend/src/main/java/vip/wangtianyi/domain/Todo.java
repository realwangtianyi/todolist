package vip.wangtianyi.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class Todo {
    private Integer id;
    private String name;
    private boolean done;
    private boolean deleted;
}
