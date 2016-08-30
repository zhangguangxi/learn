//package com.zgx;
//
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
///**
// * Created by Administrator on 2016/8/10.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@Transactional
//public class BaseTest {
//    private MockMvc mockMvc;
//    @Autowired
//    protected WebApplicationContext webApplicationContext;
//   @Before
//       public void setup() {
//           this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
//       }
//   }
