package cn.springcloud.proto;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-06 10:20
 */
public class Test1 {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        PersonModel.Person name = PersonModel.Person.newBuilder().setId(1).setName("name").setEmail("qq.com").build();
        for (byte b:name.toByteArray()){
            System.out.print(b);
        }
        System.out.println();

        PersonModel.Person person = PersonModel.Person.parseFrom(name.toByteArray());

        System.out.println(person.toString());
    }
}
