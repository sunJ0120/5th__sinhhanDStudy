package org.zerock.mreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.mreview.dto.ReviewDTO;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;
import org.zerock.mreview.repository.ReviewRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getListOfMovie(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();
        List<Review> result = reviewRepository.findByMovie(movie);
        return result.stream().map(mr -> entityToDto(mr)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO dto) {
        Review review = dtoToEntity(dto);
        reviewRepository.save(review);
        return review.getReviewnum();
    }

    @Override
    public void modify(ReviewDTO dto) {
        Optional<Review> result = reviewRepository.findById(dto.getReviewnum());
        if (result.isPresent()) {
            Review review = result.get();
            review.changeGrade(dto.getGrade());
            review.changeText(dto.getText());
            reviewRepository.save(review);
        }
    }

    @Override
    public void remove(Long reviewnum) {
        reviewRepository.deleteById(reviewnum);
    }
}
