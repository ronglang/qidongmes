$(document).ready(function(){
    			$('.tab_table').hide(0);
    			$('.tab_table:first').show(0);
    			$('.tab_nav li').click(function(){
    				var index=$(this).index();
    				$('.tab_table').eq(index).show().siblings('.tab_table').hide(0);
                    $(this).addClass('li_first').siblings().removeClass('li_first');
    			})
    		})