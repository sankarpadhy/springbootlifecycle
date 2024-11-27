package com.example.contextaware.annotations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class BeanLifecycleTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DependencyBean dependencyBean;

    @Autowired
    private DependentBean dependentBean;

    @Test
    void whenContextLoads_thenDependencyBeansAreCreated() {
        assertThat(dependencyBean).isNotNull();
        assertThat(dependentBean).isNotNull();
    }

    @Test
    void whenDevProfileActive_thenProfileSpecificBeanExists() {
        ProfileSpecificBean profileBean = applicationContext.getBean(ProfileSpecificBean.class);
        assertThat(profileBean).isNotNull();
        profileBean.performDevOperation();
    }

    @Test
    void whenLazyBeanRequested_thenItIsCreated() {
        LazyInitBean lazyBean = applicationContext.getBean(LazyInitBean.class);
        assertThat(lazyBean).isNotNull();
        lazyBean.performOperation();
    }
}
