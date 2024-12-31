package com.example.springbootlearning.Annotations.meta_annotations;

public class TargetAnnotations {
    Integer targetValue =  8;

   @targetAnnotationTest
    public  static void  targetAnnotationMethod(){
        System.out.println("targetAnnotationMethod(): invoked");
    }


}
