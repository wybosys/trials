<?php

function utc_time(){
    echo "utc\n";
    date_default_timezone_set('UTC');
    $timestamp = new \DateTime();
    $timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
    echo $timeStr . "\n";
    echo time() . "\n";
}

function gmt_time(){
    echo "gmt\n";
    date_default_timezone_set('Asia/Shanghai');
    $timestamp = new \DateTime();
    $timeStr = $timestamp->format("Y-m-d\TH:i:s\Z");
    echo $timeStr . "\n";;
    echo time()  . "\n";
}

utc_time();
gmt_time();
