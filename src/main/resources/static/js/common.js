 $("#locales").change(function () {
             var selectedOption = $('#locales').val();
             console.log(selectedOption);
             if (selectedOption != ''){
                 window.location.replace('?lang=' + selectedOption);
             }
  });