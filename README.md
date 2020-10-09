# 스프링

스프링 공부내용 정리
* 스프링 버전 5  
* java 버전 1.8

1. spring-study-01-intro
  - 스프링 프로젝트 기본 구조
2. spring-study-02-core
  - 애너테이션 사용하기 
    - @Configuration, @Bean, @Component, @Qualifier  
      @Autowire, @Primary, @Resourece, @Scope  
      @PropertySorce, @PostConstruct, @PreDestory  
      @Lazy, @DepandsOn
  - 다국어 처리 
    - MessageSource
  - 빈 인스턴스 후 처리기
    - BeanPostProcessor
  - 팩토리로 POJO 생성
    - AbstractFactoryBean
  - 프로파일(@Profile) 설정
    - 개발, 운영, 테스트 환경에 따라 프로파일 별로 빈로드 
  - Aware인터페이스 구현하여 IoC 컨테이너 리소스 인지
  - Aspect-Oriented Programming(AOP)
    - @Before, @After, @AfterReturing, @AfterThrowing, @Around
    - 기본은 다이나믹 프록시 사용 (인터페이스 기반) - @EnableAspectAutoProxy
    - CGLIB 프록시도 가능(클래스기반) - @EnableAspectAutoProxy(proxyTargetClass=true)

  
