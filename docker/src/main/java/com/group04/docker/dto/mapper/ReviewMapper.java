package com.group04.docker.dto.mapper;

import com.group04.docker.dto.ReviewRequest;
import com.group04.docker.model.Reviews;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Reviews reviewRequestToReview(ReviewRequest reviewRequest);

}
