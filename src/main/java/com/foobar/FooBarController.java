package com.foobar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foobar.bar.domain.Bar;
import com.foobar.bar.repo.BarRepository;
import com.foobar.foo.domain.Foo;
import com.foobar.foo.repo.FooRepository;

@RestController
public class FooBarController {

	private final FooRepository fooRepo;
	private final BarRepository barRepo;

	@Autowired
	FooBarController(FooRepository fooRepo, BarRepository barRepo) {
		this.fooRepo = fooRepo;
		this.barRepo = barRepo;
	}

	@RequestMapping("/foobar")
	public String all() {
		List<Foo> foos = fooRepo.findAll();
		List<Bar> bars = barRepo.findAll();

		StringBuilder builder = new StringBuilder();

		builder.append("Foos: [");
		for (Foo foo : foos) {
			builder.append(foo.getFoo() + " ");
		}
		builder.append("]");

		builder.append(" Bars: [");
		for (Bar bar : bars) {
			builder.append(bar.getBar() + " ");
		}
		builder.append("]");

		return builder.toString();
	}

	@RequestMapping("/foobar/{id}")
	public String fooBar(@PathVariable("id") Long id) {

		System.out.println("aaa");

		Foo foo = new Foo();
		if (fooRepo.findById(id).isPresent()) {
			foo = fooRepo.findById(id).get();
		}

		Bar bar = new Bar();
		if (barRepo.findById(id).isPresent()) {
			bar = barRepo.findById(id).get();
		}

		return foo.getFoo() + " " + bar.getBar() + "!";
	}

	@RequestMapping("/foobar/create/{number}")
	public String create(@PathVariable("number") Long number) {

		System.out.println("bbb");

		Foo foo = new Foo("Foo" + number);
		fooRepo.save(foo);

		Bar bar = new Bar("Bar" + number);
		barRepo.save(bar);

		return "Created " + foo.getFoo() + " " + bar.getBar() + "!";
	}

}
