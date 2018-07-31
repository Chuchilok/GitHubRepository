<?php
    header('Content-Type: application/json');

    $conn=mysqli_connect("127.0.0.1",'root','','ht',3306);
    mysqli_query($conn,"SET NAMES UTF8");

    $sql="select * from user";
    $result=mysqli_query($conn,$sql);
    $list=mysqli_fetch_all($result,MYSQLI_ASSOC);
    echo json_encode($list);