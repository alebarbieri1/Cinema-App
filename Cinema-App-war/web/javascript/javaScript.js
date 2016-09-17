/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('select').material_select();
});

// ========== SideNav JS ==========
$(".dropdown-button").dropdown();
$(".button-collapse").sideNav();
$(document).ready(function () {
    $('.modal-trigger').leanModal();
});

(function ($) {
    $(function () {

        $('.button-collapse').sideNav();

    }); // end of document ready
})(jQuery); // end of jQuery name space

/* Customizacao do SideVav:
$('.button-collapse').sideNav({
    menuWidth: 300, // Default is 240
    edge: 'right', // Choose the horizontal origin
    closeOnClick: true // Closes side-nav on <a> clicks, useful for Angular/Meteor
  }
);
*/

// ======== Fim do SideNav JS ========