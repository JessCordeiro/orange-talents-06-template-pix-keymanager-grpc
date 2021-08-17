package pix.br.com.zup

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("pix.br.com.zup")
		.start()
}

