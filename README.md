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
    - @Before, @After, @AfterReturing, @AfterThrowing, @Around, @Pointcut
    - 기본은 다이나믹 프록시 사용 (인터페이스 기반) - @EnableAspectAutoProxy
    - CGLIB 프록시도 가능(클래스기반) - @EnableAspectAutoProxy(proxyTargetClass=true)
    - Pointcut 안에는 execute(메서드기반) within(타입기반) 키워드 사용하여 조인포인트 매칭
    - 포인트컷으로 애너테이션도 사용가능
    - 인트로덕션  (@DeclareParents)
             객체가 어떤 인터페이스의 구현 클래스를 공급받아 동적으로 인터페이스를 구현
             마치 객체가 런타임에 구현 클래스를 상속하게 보이는 척하게 함  
             동시에 여러개를 인트로듀스(끌어들일)수 있어서 다중 상속도 가능함
    - POJO에 상태 추가하기
    - Aspect 위빙
      - 컴파일 타임 위빙 - ajc 전용 컴파일러가 담당
      - 로드하는 시점에 위빙(LTW) - JVM 클래스 로더를 이용해 대상 클래스를 로드하는 시점에 발생
        - 로드 타임 위버를 사용하여 처리
    - POJO를 도메인 객체에 주입
      - 컨테이너 밖에서 만든 도메인 객체에 스피링 빈을 주입한다.
    

  
