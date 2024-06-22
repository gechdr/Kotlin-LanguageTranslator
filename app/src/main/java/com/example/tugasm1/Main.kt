package com.example.tugasm1

fun main(args: Array<String>) {

    val translations: MutableMap<String, MutableList<String>> = mutableMapOf()

    var loop = true

    do {
        println("Kamus <221116958>")
        println("1. Tambah Bahasa")
        println("2. Tambah Translasi")
        println("3. Hapus Translasi")
        println("4. Translate")
        println("5. Exit")
        print(">> ")

        val menu = readlnOrNull()

        when (menu) {
            "1" -> {
                // Tambah Bahasa
                println("Tambah Bahasa")
                print("Nama Bahasa : ")
                val bahasa = readlnOrNull()
                if (bahasa != null) {
                    if (bahasa == "") {
                        println("Nama Bahasa Harus Diisi!")
                        println("")
                        continue
                    }

                    // SYNTAX KHUSUS KOTLIN 1, If / When as an expression
                    val exist = if (translations.containsKey(bahasa)) "existed" else "nonExisted"

                    if (exist == "existed") {
                        println("Nama Bahasa Sudah Ada!")
                    } else {
                        val otherLanguages = translations.keys.filter { it != bahasa }
                        if (otherLanguages.isNotEmpty()){
                            val temp = otherLanguages[0]
                            val size = translations[temp]?.size
                            translations[bahasa] = MutableList(size!!) { "" }
                        } else {
                            translations[bahasa] = mutableListOf()
                        }

                        println("Bahasa Berhasil ditambahkan!")
                    }
                } else {
                    println("Nama Bahasa Harus Diisi!")
                }
            }
            "2" -> {
                // Tambah Translasi
                println("Tambah Translasi")
                println("List Bahasa")
                println("=============")
                // SYNTAX KHUSUS KOTLIN 2, List methods (filter, map, dkk)
                translations.keys.withIndex().map { (index, bahasa) ->
                    println("${index + 1}. $bahasa")
                }
                println("=============")

                var bahasa: String? = null

                if (translations.isEmpty()) {
                    println("Belum ada bahasa yang terdaftar!")
                    println("")
                    continue
                }

                do {
                    print("Pilih Bahasa : ")
                    bahasa = readlnOrNull()
                    if (translations.containsKey(bahasa)) {
                        break
                    } else {
                        println("Bahasa Belum Terdaftar!")
                    }
                } while (bahasa == null || !translations.containsKey(bahasa))

                print("Masukan Kata dari Bahasa $bahasa: ")
                val kata = readlnOrNull()
                if (kata != null && kata != "") {
                    val exist = if (translations[bahasa]?.contains(kata) == true) "existed" else "nonExisted"
                    if (exist == "existed") {
                        val idx = translations[bahasa]?.indexOf(kata)

                        val otherLanguages = translations.keys.filter { it != bahasa }
                        for (languange in otherLanguages) {
                            val kataOther = translations[languange]?.get(idx!!)
                            if (kataOther != "") {
                                println("Translasi ke Bahasa $languange : $kataOther")
                            } else {
                                do {
                                    print("Translasi ke Bahasa $languange : ")
                                    val translasi = readlnOrNull()
                                    if (translasi != null && translasi != "") {
                                        translations[languange]?.set(idx!!, translasi)
                                    } else {
                                        println("Input Kosong!")
                                    }
                                } while (translasi == null || translasi == "")
                            }
                        }
                    } else {
                        translations[bahasa]?.add(kata)

                        val otherLanguages = translations.keys.filter { it != bahasa }
                        // SYNTAX KHUSUS KOTLIN 3, For / If range operator
                        for (i in 1..otherLanguages.size) {
                            val language = otherLanguages[i-1]
                            do {
                                print("Translasi ke Bahasa $language : ")
                                val translasi = readlnOrNull()
                                if (translasi != null && translasi != "") {
                                    translations[language]?.add(translasi)
                                } else {
                                    println("Input Kosong!")
                                }
                            } while (translasi == null || translasi == "")
                        }
                    }

                    println("Berhasil menambahkan translasi.")
                } else {
                    println("Input Kosong!")
                }
            }
            "3" -> {
                // Hapus Translasi
                println("Hapus Translasi")
                println("List Bahasa")
                println("=============")
                // SYNTAX KHUSUS KOTLIN 4, List methods (filter, map, dkk)
                translations.keys.withIndex().map { (index, bahasa) ->
                    println("${index + 1}. $bahasa")
                }
                println("=============")

                var bahasa: String? = null

                if (translations.isEmpty()) {
                    println("Belum ada bahasa yang terdaftar!")
                    println("")
                    continue
                }

                do {
                    print("Pilih Bahasa : ")
                    bahasa = readlnOrNull()
                    if (translations.containsKey(bahasa)) {
                        break
                    } else {
                        println("Bahasa Belum Terdaftar!")
                    }
                } while (bahasa == null || !translations.containsKey(bahasa))

                println("List Kata dalam Bahasa $bahasa")
                println("=============")
                val words = translations[bahasa]
                // SYNTAX KHUSUS KOTLIN 5, Elvis Operator
                words?: return
                // SYNTAX KHUSUS KOTLIN 6, Lambda Function
                words.onEachIndexed { index, kata ->
                    println("${index + 1}. $kata")
                }
                println("=============")

                var kata: String? = null

                do {
                    print("Kata yang mau dihapus : ")
                    kata = readlnOrNull()
                    if (kata == "") {
                        println("Input Kosong!")
                    }
                } while (kata == null || kata == "")

                val exist = if (translations[bahasa]?.contains(kata) == true) "existed" else "nonExisted"
                if (exist == "existed") {
                    val idx = translations[bahasa]?.indexOf(kata)
                    translations[bahasa]?.removeAt(idx!!)
                    val otherLanguages = translations.keys.filter { it != bahasa }
                    for (languange in otherLanguages) {
                        translations[languange]?.removeAt(idx!!)
                    }
                    println("Kata berhasil dihapus dari semua bahasa!")
                } else {
                    println("Kata Tidak Ada!")
                }
            }
            "4" -> {
                // Translate
                println("Translate")
                println("List Bahasa")
                println("=============")
                translations.keys.withIndex().map { (index, bahasa) ->
                    println("${index + 1}. $bahasa")
                }
                println("=============")

                if (translations.isEmpty()) {
                    println("Belum ada bahasa yang terdaftar!")
                    println("")
                    continue
                }

                var bahasaAwal: String? = null

                do {
                    print("Pilih Bahasa Awal : ")

                    bahasaAwal = readlnOrNull()
                    if (translations.containsKey(bahasaAwal)) {
                        break
                    } else {
                        println("Bahasa Belum Terdaftar!")
                    }
                } while (bahasaAwal == null || !translations.containsKey(bahasaAwal))

                var bahasaAkhir: String? = null

                do {
                    print("Translate ke Bahasa : ")
                    bahasaAkhir = readlnOrNull()
                    if (translations.containsKey(bahasaAkhir)) {
                        break
                    } else {
                        println("Bahasa Belum Terdaftar!")
                    }
                } while (bahasaAkhir == null || !translations.containsKey(bahasaAkhir))

                var kalimatAwal: String? = null

                do {
                    print("Masukan Kalimat Bahasa $bahasaAwal: ")
                    kalimatAwal = readlnOrNull()
                    if (translations.containsKey(kalimatAwal)) {
                        println("Input Kosong!")
                    }
                } while (kalimatAwal == null || kalimatAwal == "")

                val kalimat = kalimatAwal.split(" ")
                val otherLanguages = translations.keys.filter { it != bahasaAwal }
                print("Hasil Translasi ke Bahasa $bahasaAkhir : ")
                for (kata in kalimat) {
                    val idx = translations[bahasaAwal]?.indexOf(kata)
                    if (idx != null && idx != -1) {
                        val kataNew = translations[bahasaAkhir]?.get(idx)
                        if (kataNew == "") {
                            print("<Kata Belum Terdaftar>")
                        } else {
                            print("$kataNew ")
                        }
                    } else {
                        print("<Kata Belum Terdaftar>")
                    }
                }
                println()
            }
            "5" -> {
                // Exit
                loop = false
            }
            "list" -> {
                println("List Bahasa")
                println("=============")
                translations.keys.withIndex().map { (index, bahasa) ->
                    println("${index + 1}. $bahasa - ${translations[bahasa]}")
                }
                println("=============")

            }
            else -> {
                println("Menu tidak tersedia")
            }
        }

        println("")
    } while (loop)

    println("Sekian Terima Kasih.")
}