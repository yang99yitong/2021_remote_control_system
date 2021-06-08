<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
  <head>  
    <meta charset="UTF-8">
    <title>机械臂控制系统</title>
	<link rel="stylesheet" type="text/css" href="index.css">
	</head>
<body>
     <div class="header">
           <h1>机械臂远程控制系统<h1>
     </div>
     <div class="row">
           <div class="left">
                  <h2>远程操控</h2>
                  <div class="btnbox"  style="heigh:60px;">
                        <table>
                                <tr>
                                       <th><a href="Operation?operate=O"><button style="width:110px; border-radius:10px">吸取物体</button></a></th>
                                       <th><a href="Operation?operate=P"><button style="width:110px; border-radius:10px">放下物体</button></a></th>
                                </tr>
                                <tr>
                                       <th><a href="Operation?operate=K"><button style="width:110px; border-radius:10px">启动传送带</button></a></th>
                                       <th><a href="Operation?operate=L"><button style="width:110px; border-radius:10px">停止传送带</button></a></th>
                                </tr>
                                 <tr> 
                                       <th><a href="Operation?operate=H"><button style="width:110px;border-radius:10px">多轴联动</button></a></th>
                                       <th><a href="Operation?operate=J"><button style="width:110px;border-radius:10px">急停</button></a></th>
                                </tr>
                        </table>                             
                  </div>
                   <h2>修改波特率</h2>
                  <div class="btnbox"  style="heigh:60px;">
                        <table>
                           <select>
                              <option>请选择</option>
                              <option>96000</option>
                              <option>115200</option>
                           </select>
                        </table>                             
                  </div>
                  
           </div>
           <div class="medium"><h2>电机部件检测</h2>
                  <div class="btnbox" style="heigh:60px;">
                     <table style="text-align:center;">
                        <thead>
                               <tr>
                                      <th>&nbsp</th>
                                      <th>顺时针转</th>
                                      <th>逆时针</th>
                               </tr>
                        </thead>
                        <tbody>
                                <tr>
                                       <th>第一轴</th>
                                       <td><a href="Operation?operate=W"><button style="width:80px; border-radius:10px">W</button></a></td>
                                       <td><a href="Operation?operate=Q"><button style="width:80px; border-radius:10px">Q</button></a></td>
                                </tr>
                                <tr>
                                       <th>第二轴</th>
                                       <td><a href="Operation?operate=S"><button style="width:80px; border-radius:10px">S</button></a></td>
                                       <td><a href="Operation?operate=A"><button style="width:80px; border-radius:10px">A</button></a></td>
                                </tr>
                                <tr>
                                       <th>第三轴</th>
                                       <td><a href="Operation?operate=X"><button style="width:80px; border-radius:10px">X </button></a></td>
                                       <td><a href="Operation?operate=Z"><button style="width:80px; border-radius:10px">Z </button></a></td>
                                </tr>
                                <tr>
                                       <th>第四轴</th>
                                       <td><a href="Operation?operate=R"><button style="width:80px; border-radius:10px">R </button></a></td>
                                       <td><a href="Operation?operate=E"><button style="width:80px; border-radius:10px">E </button></a></td>
                                </tr>
                                <tr>
                                       <th>第五轴</th>
                                       <td><a href="Operation?operate=F"><button style="width:80px; border-radius:10px">F </button></a></td>
                                       <td><a href="Operation?operate=D"><button style="width:80px; border-radius:10px">D</button></a></td>
                                </tr>
                                <tr>
                                       <th>第六轴</th>
                                       <td><a href="Operation?operate=V"><button style="width:80px; border-radius:10px">V </button></a></td>
                                       <td><a href="Operation?operate=C"><button style="width:80px; border-radius:10px">C </button></a></td>
                                </tr>
                        </tbody>
                     </table>
                  </div>
           </div>
           <div class="right">
           <h2>机械臂示意图</h2>
           <p><img alt="jixiebi" src="./images/model.png"></p>
           </div>
    </div>
	<script src="jquery-3.3.1.min.js"></script>
	<script src="control.js"></script>
</body>

</html>
