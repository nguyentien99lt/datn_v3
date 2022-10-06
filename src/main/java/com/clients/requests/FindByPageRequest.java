package com.clients.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindByPageRequest {
    private Integer pageSize;
    private Integer pageNumber;
}
