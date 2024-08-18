$(function () {


  $('#merchandiseList tbody tr').on('click', function () {
    $('#merchandiseList tbody tr').removeClass('table-active');
    $(this).addClass('table-active');
    $('#editBtn').removeAttr('disabled');

    // 商品ID一時保管
    editSelectedMerchandiseId($(this));
  });




})


function editSelectedMerchandiseId(row) {
  row.find('td').each(function () {
    var columnId = $(this).attr('id');
    if (columnId.startsWith('merchandiseId_')) {
      $('#selectedMerchandiseId').val($(this).text());
      return false;
    }
  })
}