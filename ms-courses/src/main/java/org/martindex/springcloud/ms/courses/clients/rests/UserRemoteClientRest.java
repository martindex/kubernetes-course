package org.martindex.springcloud.ms.courses.clients.rests;

import java.util.List;
import org.martindex.springcloud.ms.courses.clients.remotes.UserRemote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ms-users", url="localhost:8001")
public interface UserRemoteClientRest {
    @GetMapping("/{id}")
    UserRemote getUserById(@PathVariable Long id);

    @PostMapping
    UserRemote createUser(@RequestBody UserRemote UserRemote);

    @GetMapping("/list")
    List<UserRemote> getUserListByIds(@RequestParam Iterable<Long> ids);
}
