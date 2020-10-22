package com.study.mvc.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 *  <members>
 *      <member>
 *          <email>marten@deinum.biz</email>
 *          <name>Marten Deinum</name>
 *          <phone>00-31-1234567890</phone>
 *      </member>
 *      <member>
 *          <email>john@doe.com</email>
 *          <name>John Doe</name>
 *          <phone>1-800-800-800</phone>
 *      </member>
 *      <member>
 *          <email>jane@doe.com</email>
 *          <name>Jane Doe</name>
 *          <phone>1-801-802-803</phone>
 *      </member>
 *  </members>
 */
@XmlRootElement //Jaxb2Marshaller가 클래스 필드를 자동 감지해 XML 데이터로 바꾸도록 지시한다.
@XmlAccessorType(XmlAccessType.FIELD)
public class Members {

    @XmlElement(name="member")
    private List<Member> members = new ArrayList<>();

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMembers(Collection<Member> members) {
        this.members.addAll(members);
    }
}
