 $('[name="locales"]').change(function () {
             var selectedOption = $('[name="locales"]').val();
             console.log(selectedOption);
             if (selectedOption != ''){
                 window.location.replace('?lang=' + selectedOption);
             }
  });