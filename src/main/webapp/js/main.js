/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('document').ready(function(){
    $('.submit-span').on('click', function() {
  $(this).closest('form').submit();
});

$('.debateBox').on('click', function(){
   // $.get("/OpenDebate/pages",{content: "getDebate"});
    window.location.assign(".?action=debate&command=getDebate&id="+this.id);
});

$(".debateMonth").stick_in_parent();
});

