package vip.wangtianyi.domain;

import lombok.Data;

@Data
public class Todo {
    private Integer id;
    private String name;
    private boolean done;
    private boolean deleted;
}
