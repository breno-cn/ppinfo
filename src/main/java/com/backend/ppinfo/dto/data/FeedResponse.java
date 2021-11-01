package com.backend.ppinfo.dto.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FeedResponse {

    List<BoardResponse> boards;

}
