$(function () {

  // when the user clicks on the element with the ID deleteOkBtn, it will
  // cause the element with the ID deleteBtn to behave as if it was clicked.
  $('#deleteOkBtn').click(function () {
    $('#deleteBtn').trigger("click");
  });

})