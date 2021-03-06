package com.lob.demoinflearnrestapi.events;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder @AllArgsConstructor @NoArgsConstructor // 롬복 어노테이션은 다른 어노테이션과 다르게 메타 어노테이션을 통한 사용이 불가능하다.
@Getter @Setter @EqualsAndHashCode(of = "id") // of id? entity 간의 연관 관계가 있을 때 상호 참조하는 관계가 되어버리면 스택오버플로우가 발생 가능
@Entity
public class Event {                          // id의 값만 가지고 equals와 hashcode를 비교함 연관 관계에 해당하는 녀석은 넣으면 안된다

    @Id @GeneratedValue
    private Integer id; // 임의로 엔티티에 setter를 통해 id를 저장 id를 반환하는 것은 DB에 정상적으로 저장했다는 것을 알리기도 하지만 사용자가 해당 id를 이용해 값을 조회할 때 쓰기도 함
    private String name; // 명
    private String description; // 설명
    private LocalDateTime beginEnrollmentDateTime; // 시작 일시
    private LocalDateTime closeEnrollmentDateTime; // 종료 일시
    private LocalDateTime beginEventDateTime; // 이벤트 시작 일시
    private LocalDateTime endEventDateTime; // 이벤트 종료 일시
    private String location; // (optional) 이게 없으면 온라인 모임
    private int basePrice; // (optional)
    private int maxPrice; // (optional)
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    @Enumerated(EnumType.STRING) // 기본 값은 ORDINAL 숫자 값으로 인덱싱이 되는데 나중에 Enum의 순서가 바뀌거나 변경되었을 경우 데이터가 꼬인다.
    private EventStatus eventStatus = EventStatus.DRAFT;


    public void update() {
        if (this.basePrice == 0 && this.maxPrice == 0) {
            this.free = true;
        } else {
            this.free = false;
        }

        if (this.location == null || this.location.isBlank()) {
            this.offline = false;
        } else {
            this.offline = true;
        }
    }
}
