/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.^extension.funcelectrical.html

import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance
import de.dlr.sc.virsat.model.^extension.funcelectrical.model.Interface
import de.dlr.sc.virsat.model.^extension.funcelectrical.model.InterfaceEnd
import de.dlr.sc.virsat.model.^extension.funcelectrical.model.InterfaceType
import de.dlr.sc.virsat.model.^extension.funcelectrical.model.InterfaceTypeCollection
import java.time.LocalDateTime
import java.util.List

class HTMLExporter {
	
	def String getElementsRecursively(StructuralElementInstance sc)
	'''
		{name: '«sc.getName()»',
			
			«IF sc.getChildren().size() != 0 »
				children: [
			
				«FOR  c : sc.getChildren() »
		 				«getElementsRecursively(c)»
		 				«IF c != sc.getChildren().get(sc.getChildren().size()-1)»
		 					,
		 				«ENDIF»
				«ENDFOR»
			]
			«ENDIF»
		
		}
	'''
	
		def getSubPages(StructuralElementInstance sc, List<InterfaceEnd> seiInterfaceEnds, List<Interface> seiInterfaces, List<InterfaceType> seiInterfaceTypes, List<SEILink> seiLinks)
	'''
		<style type="text/css">
		@media screen
		{
		    #forprint {
		    	display: none;
		    }
		    #footerHolder {
		    	display: none;
		    }
		}
			@media print
				{    
				    #button
				    {
				        display: none !important;
					}
					a[href]:after {
					    content: none !important;
					}
					a {
					  color: inherit;
					  text-decoration: inherit;
					}
					#forprint {
						display: initial;
					}
					#footerHolder {
				    	display: initial;
				    }
				}
		    table tr:nth-child(odd) td {
		        background-color: #EEEEEF;
		    }
		    
		    table tr:nth-child(even) td {
		        background-color: #FFFFFF;
		    }
		    footer {
				font-family: 'DejaVu Sans Mono', monospace;
				font-size: 14px;
				font-weight: bold;
		    	height: 5px;
		        display: table-row;
		        text-align: center;
		        vertical-align: middle;
		        top: 50%;
		    }
		    .tabEnd {
		        text-align: left;
		        background-repeat: no-repeat;
		        color: #253441;
		        font-weight: bold;
		        clear: none;
		        overflow: hidden;
		        padding-top: 10px;
		        padding-bottom: 10px;
		        padding-left: 1px;
		        margin-top: 20px;
		        white-space: pre;
		        margin-right: 3px;
		        float: left;
		        background-color: #F8981D;
		        height: 16px;
		    }
		    
			tr,
			td,
			th {
		       font-family: 'DejaVu Sans Mono', monospace;
		       font-size: 14px;
		       font-weight: bold;
		       border: 1px solid #EEE;
		       color: #253441;
		       padding: 0px;padding-top: 10px;
		       padding-left: 1px;
		    
		    }
		    td {
		       vertical-align: middle;
		       padding-bottom: 10px;
		    }
		    th {
		       background: #dee3e9;
		       text-align: left;
		       padding: 8px 3px 3px 7px;
		    } 
		    table{
		    	border: 1px solid #EEE;
		    	color: #253441;
		    	padding: 0px;padding-top: 10px;
		    	padding-left: 1px;
		    	width: 100%;
		    }      
		    #data {
		        width: 95%;
		        float: left;
		        overflow: hidden;
		        padding-top: 30px;
		        padding-right: 30px;
		        padding-bottom: 30px;
		        padding-left: 30px;
		    }
		    #noprint {
		    	display:none;
		    }
		    h1 {
		        text-align: center;
		    }
		    #button {
		    	background-image: url(PrinterLogo.png);
		    	background-size: cover;
				width: 70px;
				height: 70px;
				padding: 10px;
				line-height: 50px;
				text-align: left;
				text-indent: 10px;
				font-family: Verdana, Geneva, sans-serif;
				font-size: 16px;
				color: #333;
				-webkit-border-radius: 5px;
				-moz-border-radius: 5px;
				border-radius: 5px;
				border: 1px solid #333;
				cursor: pointer;
				margin: 20px;
		    }
		</style>
		<!DOCTYPE html>
		<html>
		<div id="forprint">
			<img src="ProjectLogo.png" alt="" />
		</div>
		<button id = "button" onclick="myFunction()"></button>
		<script>
		function myFunction() {
		    window.print();
		}
		</script>
		«IF seiLinks!== null»
			«FOR seiLink : seiLinks »
				«getSeiLinkHTML(sc, seiLink)»
			«ENDFOR»
		«ENDIF»
		<div id="data">
		    
		    <table>
		    	<caption><span class="tabEnd"> Element </span></caption>
		    	<colgroup>
		           <col span="1" style="width: 50%;">
		           <col span="1" style="width: 50%;">
		        </colgroup>
		        <tr>
		            <th >Element Name </th>
		            <td >«sc.getName()»</td>
		        </tr>
		        <tr>
		            <th>Element UUID</th>
		            <td>«sc.getUuid()»</td>
		        </tr>
		        <tr>
		            <th>Assigned Discipline</th>
		            <td>
		            «IF sc.getAssignedDiscipline()!== null»
		            	«sc.getAssignedDiscipline().getName()»   
		            «ENDIF»
		            </td>
		        </tr>
		        <tr>
		           <th>Revision Number</th>    	
		           <td>    </td>	            
		        </tr>
		        <tr>
		           <th>Checksum</th>    	
		           <td>«CheckSumCreator.getObjectChecksum(sc)»</td>	            
		        </tr>
		    </table>
		   
			 «IF sc.getType().getName() != InterfaceTypeCollection.getClass.getName()»
			    
			    <table>
			    	<caption><span class="tabEnd"> Interface Ends </span></caption>
			        <tr>
			            <th>InterfaceEndName</th>
			            <th>InterfaceEndType</th>
			        </tr>
			        «FOR ie : seiInterfaceEnds »
			        <tr>
			            <td>«ie.getName()»</td>
			            <td>«IF ie.getType() !== null»  «ie.getType().getName()»  «ENDIF»</td>
			        </tr>
			        «ENDFOR»
			    </table>
			   «getInterfaceTable(seiInterfaces)»
			«ENDIF»
			«IF sc.getType().getName() == InterfaceTypeCollection.getClass().getSimpleName()»
			<table>
				<caption><span class="tabEnd"> InterfaceTypes </span></caption>
				<tr>
					<th>InterfaceType</th>
				</tr>

				«FOR ie : seiInterfaceTypes »
				<tr>
					<td>«ie.getName()»</td> 
				</tr>	
			    «ENDFOR»

			</table>
			«ENDIF»
		</div>
		
			<footer>
				<div id = "footerHolder">
		  		«var ldt = LocalDateTime.now()» Exported by : «UserRegistry.getInstance().getUserName()» Export date : «ldt.getHour() + ":" + ldt.getMinute() + " " + ldt.getDayOfMonth() + "/" + ldt.getMonthValue() + "/" + ldt.getYear()»
				</div>
			</footer>
		
		</html>
	'''
	
	def CharSequence getInterfaceTable(List<Interface> interfaces) {
	    '''<table>
	     <caption><span class="tabEnd"> Interfaces </span></caption>
	        <tr>
	            <th>InterfaceName</th>
	            <th>FromInterfaceEnd</th>
	            <th>Container</th>
	            <th>ToInterfaceEnd</th>
	            <th>Container</th>
	 
	        </tr>
	        «FOR i : interfaces.filter[getInterfaceEndFrom() !== null && getInterfaceEndTo() !== null] »	
	        <tr>
	        	« var StructuralElementInstance temp = i.getInterfaceEndFrom().getTypeInstance().eContainer() as StructuralElementInstance»
	        	« var StructuralElementInstance temp2 = i.getInterfaceEndTo().getTypeInstance().eContainer() as StructuralElementInstance»
	            <td>«i.getName()»</td>
	            <td>«IF i.getInterfaceEndFrom() !== null» <a href="«temp.getName()».htm"> «i.getInterfaceEndFrom().getTypeInstance().getName()» </a> «ENDIF»</td>
	            <td>«IF i.getInterfaceEndFrom() !== null» <a href="«temp.getName()».htm"> «temp.getName()» </a> «ENDIF»</td>
	            <td>«IF i.getInterfaceEndTo() !== null» <a href="«temp2.getName()».htm"> «i.getInterfaceEndTo().getTypeInstance().getName()» «ENDIF»</td>
	            <td>«IF i.getInterfaceEndFrom() !== null» <a href="«temp2.getName()».htm"> «temp2.getName()» </a> «ENDIF»</td>
	        </tr>
	        «ENDFOR»
	    </table>'''
	}
	
	def CharSequence getSeiLinkHTML(StructuralElementInstance sc, SEILink seiLink)
		'''«IF sc.getName() == seiLink.getContainerSEIname()»
			<h1 ><img src= "«sc.getName()».png" align="middle" alt="alttext" usemap="#mapname" > </h1>
			<map name="mapname">
				«FOR area : seiLink.getAreas()»
				<area shape="rect" coords="«area.getTopLeftx()»,«area.getTopLefty()»,«area.getBottomRightx()»,«area.getBottomRighty()»" href="«area.getLinkSEIname()».htm" alt="alttext">
				«ENDFOR»
			</map>
		«ENDIF»'''
	
	
	
	def someHTML(StructuralElementInstance sc) 
	
	'''
		<!DOCTYPE html>
		<html>
		
		<script type="text/javascript" src="https://cdn.jsdelivr.net/vue/1.0.24/vue.js"></script>

		<style type="text/css">
			@media print
			{    
			    #tree
			    {
			        display: none !important;
				}
			}
		    body {
		        font-family: Menlo, Consolas, monospace;
		        color: #444;
		    }
		    
		    .item {
		        cursor: pointer;
		    }
		    
		    .bold {
		        font-weight: bold;
		    }
		    
		    .helper {
		        display: inline-block;
		        height: 100%;
		        vertical-align: middle;
		    }
		    
		    ul {
		        padding-left: 1em;
		        line-height: 1.5em;
		        list-style-type: dot;
		    }
		    
		    html,
		    body {
		        height: 100%;
		        min-height: 100%;
		    }
		    
		    #generalwrapper {
		        height: 100%;
		        min-height: 100%;
		        width: 100%;
		        border: 3px solid #4A8AB8;
		        display: table;
		    }
		    
		    #wrapper {
		        height: 100%;
		        min-height: 100%;
		        display: table-row;
		        display: table;
		        width: 100%;
		    }
		    
		    #datadiv {
		        height: 100%;
		        min-height: 500px;
		        min-width: 500px;
		        width: 70%;
		        object-fit: contain;
		        display: table-cell;
		        overflow:visible;
		    }
		    
		    #tree {
		        border-right: 15px solid #4A8AB8;
		        color: #4A8AB8;
		        vertical-align: top;
		        display: table-cell;
		        width: 5%;
		    }
		    
		    header,
		    footer {
		    	height: 5px;
		        width: 100%;
		        color: white;
		        background-color: #4A8AB8;
		        display: table-row;
		        text-align: center;
		        vertical-align: middle;
		        top: 50%;
		    }
		    
		    article {
		        float: left;
		        padding: 1em;
		        overflow: hidden;
		    }
		</style>
		
		<script type='text/javascript'>
		    //<![CDATA[
		    window.onload = function() {
		            // demo data
		            var data = «getElementsRecursively(sc)»
		            // define the item component
		            Vue.component('item', {
		                template: '#item-template',
		                props: {
		                    model: Object
		                },
		                data: function() {
		                    return {
		                        open: false
		                    }
		                },
		                computed: {
		                    isFolder: function() {
		                        return this.model.children &&
		                            this.model.children.length
		                    }
		                },
		                methods: {
		                    toggle: function() {
		                        if (this.isFolder) {
		                            this.open = !this.open
		                        }
		                        var name = this.model.name
		                        var url = "resources/" + name + ".htm"
		                        var start = new Date().getTime()
		                        document.getElementById("datadiv").innerHTML = '<object width=100% height=100% type="text/html" data="' + url + '" onload="alert(new Date().getTime() - start)"></object>'
		                    }
		                }
		            })
		            // boot up the demo
		            var demo = new Vue({
		                el: '#demo',
		                data: {
		                    treeData: data
		                }
		            })	
		        } //]]>
		</script>	
		<script type="text/x-template" id="item-template">
		    <li>
		        <div :class="{bold: isFolder}" @click="toggle">
		            {{model.name}}
		            <span v-if="isFolder">[{{open ? '-' : '+'}}]</span>
		        </div>
		        <ul v-show="open" v-if="isFolder">
		            <item class="item" v-for="model in model.children" :model="model">
		            </item>
		        </ul>
		    </li>
		</script>
		
		<div id="generalwrapper">
		    <header>
		
		        This Document has been generated using Virtual Satellite 4 Core.
	
		    </header>
		    <div id="wrapper">

		        <div id="tree">
		            <img src="resources/ProjectLogo.png" alt="" />
		            <article>
		                <ul id="demo">
		                    <item class="item" :model="treeData">
		                    </item>
		                </ul>
		            </article>
		        </div>
		        <div id="datadiv">
		            Click on the tree to see the interfaces and interface ends
		        </div>
		
		    </div>
		    <footer>
		       Exported by : «UserRegistry.getInstance().getUserName()» Export date : «getTimestamp()»
		    </footer>
		
		</div>	
		</html>

'''	
	def getTimestamp() 
	'''
		«var ldt = LocalDateTime.now()»
		«ldt.getHour() + ":" + ldt.getMinute() + " " + ldt.getDayOfMonth() + "/" + ldt.getMonthValue() + "/" + ldt.getYear()»
		
	'''
}