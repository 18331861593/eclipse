<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
		 href="ext-5.0.0/packages/ext-theme-classic/build/resources/ext-theme-classic-all.css">
	<script type="text/javascript" src="ext-5.0.0/ext-all.js"> </script>
	<script type="text/javascript" 
		src="ext-5.0.0/packages/ext-theme-classic/build/ext-theme-classic.js"></script>
	<script type="text/javascript" src="scripts/hello.js"></script>
</head>
<body>

</body>
<script type="text/javascript">
	Ext.onReady(function(){
		var autoPanel = Ext.create('Ext.panel.Panel',{
			title : 'autoPanel',
			width : 1200,
			height : 600,
			renderTo : Ext.getBody(), 

		    layout: {
		        type: 'border'
		    },

		    items: [{
		        xtype: 'panel',
		        bind: {
		            title: '{name}'
		        },
		        region: 'west',
		        html: '<ul><li>This area is commonly used for navigation, for example, using a "tree" component.</li></ul>',
		        width: 250,
		        split: true,
		        tbar: [{
		            text: 'Button',
		            handler: 'onClickButton'
		        }]
		    },{
		        region: 'center',
		        xtype: 'tabpanel',
		        items:[{
		            title: 'Tab 1',
		            html: '<h2>Content appropriate for the current navigation.</h2>'
		        }]
		    }]
		});
		
	});
</script>
</html>