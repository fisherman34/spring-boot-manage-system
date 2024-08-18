package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Sam
 * @create 2024-08-14 9:03 PM
 */

@Data
public class PaginatedResponse<T> {

  private List<T> content;
  private Integer pageNumber; // current page number (0-indexed)
  private Integer pageSize;
  private Long totalElements;
  private Integer totalPages;
  private Integer firstPagesForShow; // index from 0
  private Integer lastPagesFowShow; // index from 0

  // Empty constructor
  public PaginatedResponse() {
  }

  // All-fields constructor
  public PaginatedResponse(List<T> content, Integer pageNumber, Integer pageSize, Long totalElements, Integer totalPages) {
    this.content = content;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.totalElements = totalElements;
    this.totalPages = totalPages;
    this.firstPagesForShow = Math.max(0, pageNumber -3);
    this.lastPagesFowShow = upperbound(pageNumber, totalPages);
  }

  /**
   * ページネーションの最終ページを計算します。
   *
   * @param pageNumber
   * @param totalPages
   * @return
   */
  private Integer upperbound(Integer pageNumber, Integer totalPages) {
    Integer totalMinus1 = totalPages - 1;
    if (totalMinus1 < 0) {
      return 0;
    } else {
      return Math.min(pageNumber + 3, totalPages -1);
    }
  }

}
