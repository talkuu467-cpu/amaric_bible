package com.example.data

import com.example.data.model.BibleBook
import com.example.data.model.BibleVerse
import com.example.data.model.BookCategory
import com.example.data.model.DailyVerse
import com.example.data.model.ReadingPlan
import com.example.data.model.ReadingPlanDay
import com.example.data.model.Testament
import com.example.data.model.TopicalVerse

object BibleDataProvider {

    val books: List<BibleBook> = listOf(
        // Old Testament - Law (ብሉይ ኪዳን - ኦሪትና ሕግ)
        BibleBook(1, "Genesis", "ኦሪት ዘፍጥረት", "Gen", "ዘፍ", Testament.OLD, BookCategory.LAW, 50, "The book of beginnings: Creation, the Fall, the Flood, and the Patriarchs.", "የመጀመሪያው መጽሐፍ፡ ፍጥረት፣ የሰው ውድቀት፣ የኖኅ መርከብ እና የአባቶች ታሪክ።"),
        BibleBook(2, "Exodus", "ኦሪት ዘጸአት", "Exo", "ዘጸ", Testament.OLD, BookCategory.LAW, 40, "Deliverance from Egypt, the Red Sea crossing, the Ten Commandments, and the Tabernacle.", "ከግብፅ ባርነት መውጣት፣ የኤርትራ ባሕር መከፈል፣ አሥርቱ ቃላትና የማደሪያው ድንኳን።"),
        BibleBook(3, "Leviticus", "ኦሪት ዘሌዋውያን", "Lev", "ዘሌ", Testament.OLD, BookCategory.LAW, 27, "Laws of holiness, sacrifices, and the priesthood.", "የቅድስና ሕጎች፣ መሥዋዕቶች እና የክህነት ሥርዓት።"),
        BibleBook(4, "Numbers", "ኦሪት ዘኍልቍ", "Num", "ዘኍ", Testament.OLD, BookCategory.LAW, 36, "The wilderness wanderings of the children of Israel.", "የእስራኤል ልጆች በምድረ በዳ ያደረጉት ጉዞና የሕዝቡ ቍጥር።"),
        BibleBook(5, "Deuteronomy", "ኦሪት ዘዳግም", "Deu", "ዘዳ", Testament.OLD, BookCategory.LAW, 34, "Moses' final discourses and covenant renewal before entering the Promised Land.", "የሙሴ የመጨረሻ ምክሮችና ቃል ኪዳኑን ወደ ተስፋይቱ ምድር ከመግባታቸው በፊት ማደስ።"),

        // Old Testament - History (ታሪክ)
        BibleBook(6, "Joshua", "መጽሐፈ ኢያሱ", "Jos", "ኢያ", Testament.OLD, BookCategory.HISTORY, 24, "Conquest and partition of Canaan under Joshua's leadership.", "በኢያሱ መሪነት ከነዓንን መውረስና ምድሪቱን ማከፋፈል።"),
        BibleBook(7, "Judges", "መጽሐፈ መሳፍንት", "Jdg", "መሳ", Testament.OLD, BookCategory.HISTORY, 21, "Cycles of disobedience, deliverance, and judges in Israel.", "የእስራኤል ማፈንገጥ፣ የእግዚአብሔር ምሕረትና የመሳፍንት ዘመን።"),
        BibleBook(8, "Ruth", "መጽሐፈ ሩት", "Rut", "ሩት", Testament.OLD, BookCategory.HISTORY, 4, "A beautiful story of loyalty, redemption, and grace.", "የታማኝነት፣ የቤዛነትና የጸጋ ድንቅ ታሪክ።"),
        BibleBook(9, "1 Samuel", "መጽሐፈ ሳሙኤል ቀዳማዊ", "1Sa", "1ሳሙ", Testament.OLD, BookCategory.HISTORY, 31, "The transition from judges to monarchy: Samuel, Saul, and David.", "ከመሳፍንት ወደ ንግሥና፡ ሳሙኤል፣ ሳኦል እና ዳዊት።"),
        BibleBook(10, "2 Samuel", "መጽሐፈ ሳሙኤል ካልዕ", "2Sa", "2ሳሙ", Testament.OLD, BookCategory.HISTORY, 24, "The reign and victories of King David.", "የንጉሥ ዳዊት ዘመነ መንግሥትና ድሎች።"),
        BibleBook(11, "1 Kings", "መጽሐፈ ነገሥት ቀዳማዊ", "1Ki", "1ነገ", Testament.OLD, BookCategory.HISTORY, 22, "Solomon's temple, reign, and the division of the kingdom.", "የሰሎሞን ቤተ መቅደስ፣ ጥበቡና የመንግሥቱ መከፈል::"),
        BibleBook(12, "2 Kings", "መጽሐፈ ነገሥት ካልዕ", "2Ki", "2ነገ", Testament.OLD, BookCategory.HISTORY, 25, "The divided kingdoms of Israel and Judah and their exile.", "የእስራኤልና የይሁዳ ነገሥታት ታሪክ እስከ ባቢሎን ምርኮ።"),
        BibleBook(13, "1 Chronicles", "መጽሐፈ ዜና መዋዕል ቀዳማዊ", "1Ch", "1ዜና", Testament.OLD, BookCategory.HISTORY, 29, "Genealogies and David's spiritual heritage.", "የትውልድ ሐረግና የዳዊት መንፈሳዊ ቅርስ።"),
        BibleBook(14, "2 Chronicles", "መጽሐፈ ዜና መዋዕል ካልዕ", "2Ch", "2ዜና", Testament.OLD, BookCategory.HISTORY, 36, "Solomon and the kings of Judah leading to exile.", "ሰሎሞንና የይሁዳ ነገሥታት እስከ መቅደሱ መታደስ።"),
        BibleBook(15, "Ezra", "መጽሐፈ ዕዝራ", "Ezr", "ዕዝ", Testament.OLD, BookCategory.HISTORY, 10, "Return from Babylonian exile and rebuilding of the temple.", "ከምርኮ መመለስና ቤተ መቅደሱን እንደገና መሥራት።"),
        BibleBook(16, "Nehemiah", "መጽሐፈ ነህምያ", "Neh", "ነህ", Testament.OLD, BookCategory.HISTORY, 13, "Rebuilding the walls of Jerusalem under Nehemiah.", "በነህምያ መሪነት የኢየሩሳሌም ቅጥር መታደስ።"),
        BibleBook(17, "Esther", "መጽሐፈ አስቴር", "Est", "አስ", Testament.OLD, BookCategory.HISTORY, 10, "God's sovereign protection of His people through Queen Esther.", "በንግሥት አስቴር አማካኝነት እግዚአብሔር ሕዝቡን ያዳነበት ድንቅ ታሪክ።"),

        // Old Testament - Poetry & Wisdom (ቅኔና ጥበብ)
        BibleBook(18, "Job", "መጽሐፈ ኢዮብ", "Job", "ኢዮ", Testament.OLD, BookCategory.POETRY, 42, "Faith and suffering, testing, and God's sovereign wisdom.", "በመከራ ውስጥ ያለ እምነትና የእግዚአብሔር ጥበብ።"),
        BibleBook(19, "Psalms", "መዝሙረ ዳዊት", "Psa", "መዝ", Testament.OLD, BookCategory.POETRY, 150, "Inspired prayers, praise, laments, and worship songs.", "የምስጋና፣ የጸሎት፣ የእምነትና የአምልኮ መዝሙራት።"),
        BibleBook(20, "Proverbs", "መጽሐፈ ምሳሌ", "Pro", "ምሳ", Testament.OLD, BookCategory.POETRY, 31, "Practical wisdom, fear of the Lord, and moral guidance.", "የጥበብና የተግሣጽ ቃላት፣ እግዚአብሔርን መፍራት።"),
        BibleBook(21, "Ecclesiastes", "መጽሐፈ መክብብ", "Ecc", "መክ", Testament.OLD, BookCategory.POETRY, 12, "The search for meaning in life and finding fulfillment in God.", "የሕይወት ትርጉም ፍለጋና እግዚአብሔርን ማክበር።"),
        BibleBook(22, "Song of Solomon", "ማሕልየ መሓልይ", "Sng", "ማሕ", Testament.OLD, BookCategory.POETRY, 8, "The celebration of sacred covenant love and marriage.", "የንጹሕ ፍቅርና የጋብቻ ቅዱስ ማሕሌት።"),

        // Old Testament - Major Prophets (ታላላቅ ነቢያት)
        BibleBook(23, "Isaiah", "ትንቢተ ኢሳይያስ", "Isa", "ኢሳ", Testament.OLD, BookCategory.MAJOR_PROPHETS, 66, "The Messianic prophet: judgment, comfort, and the Suffering Servant.", "ስለ መሲሑ፣ ስለ መከራውና ስለ ወደፊቱ ክብር የተነገረ ትንቢት።"),
        BibleBook(24, "Jeremiah", "ትንቢተ ኤርምያስ", "Jer", "ኤር", Testament.OLD, BookCategory.MAJOR_PROPHETS, 52, "The weeping prophet: warning of exile and promise of a New Covenant.", "የአዲሱ ቃል ኪዳን ተስፋና የተግሣጽ ቃል::"),
        BibleBook(25, "Lamentations", "ሰቆቃወ ኤርምያስ", "Lam", "ሰቆ", Testament.OLD, BookCategory.MAJOR_PROPHETS, 5, "Poetic mourning over the destruction of Jerusalem, with God's faithful mercy.", "ስለ ኢየሩሳሌም መፍረስ የተደረገ ልቅሶና የእግዚአብሔር ታማኝነት።"),
        BibleBook(26, "Ezekiel", "ትንቢተ ሕዝቅኤል", "Ezk", "ሕዝ", Testament.OLD, BookCategory.MAJOR_PROPHETS, 48, "Visions of God's glory, dry bones resurrected, and the future temple.", "የእግዚአብሔር ክብር ራእይና የደረቁ አጥንቶች መነሣት።"),
        BibleBook(27, "Daniel", "ትንቢተ ዳንኤል", "Dan", "ዳን", Testament.OLD, BookCategory.MAJOR_PROPHETS, 12, "Courage in Babylon, prophetic empires, and God's everlasting kingdom.", "የዳንኤል ጽናት በባቢሎንና የዘላለም መንግሥት ራእይ።"),

        // Old Testament - Minor Prophets (ደቂቀ ነቢያት)
        BibleBook(28, "Hosea", "ትንቢተ ሆሴዕ", "Hos", "ሆሴ", Testament.OLD, BookCategory.MINOR_PROPHETS, 14, "God's unfailing redeeming love for unfaithful Israel.", "የእግዚአብሔር የማይለወጥ አፍቃሪነት ለእስራኤል።"),
        BibleBook(29, "Joel", "ትንቢተ ኢዩኤል", "Jol", "ኢዩ", Testament.OLD, BookCategory.MINOR_PROPHETS, 3, "The Day of the Lord and the outpouring of the Holy Spirit.", "የመንፈስ ቅዱስ መፍሰስና የእግዚአብሔር ቀን።"),
        BibleBook(30, "Amos", "ትንቢተ አሞጽ", "Amo", "አሞ", Testament.OLD, BookCategory.MINOR_PROPHETS, 9, "Justice, righteousness, and warning to the complacent.", "ጽድቅ እንደ ወንዝ፣ ፍርድም እንደማይደርቅ ፈሳሽ ይፍሰስ።"),
        BibleBook(31, "Obadiah", "ትንቢተ አብድዩ", "Oba", "አብ", Testament.OLD, BookCategory.MINOR_PROPHETS, 1, "Judgment on Edom for pride and hostility.", "በትዕቢተኞች ላይ የሚመጣ ፍርድ።"),
        BibleBook(32, "Jonah", "ትንቢተ ዮናስ", "Jon", "ዮና", Testament.OLD, BookCategory.MINOR_PROPHETS, 4, "God's universal mercy extending even to Nineveh.", "የእግዚአብሔር ምሕረት ለነነዌ ሰዎች።"),
        BibleBook(33, "Micah", "ትንቢተ ሚክያስ", "Mic", "ሚክ", Testament.OLD, BookCategory.MINOR_PROPHETS, 7, "Act justly, love mercy, walk humbly; Bethlehem prophesied.", "ቅን ፍርድ ማድረግ፣ ምሕረትን መውደድ፣ በትሕትና መመላለስ።"),
        BibleBook(34, "Nahum", "ትንቢተ ናሆም", "Nah", "ናሆ", Testament.OLD, BookCategory.MINOR_PROPHETS, 3, "The downfall and judgment of Nineveh.", "የእግዚአብሔር ፍርድና ኃያልነት።"),
        BibleBook(35, "Habakkuk", "ትንቢተ ዕንባቆም", "Hab", "ዕን", Testament.OLD, BookCategory.MINOR_PROPHETS, 3, "The just shall live by faith; praise in adversity.", "ጻድቅ በእምነቱ በሕይወት ይኖራል።"),
        BibleBook(36, "Zephaniah", "ትንቢተ ሶፎንያስ", "Zep", "ሶፎ", Testament.OLD, BookCategory.MINOR_PROPHETS, 3, "Purification and restoration for a humble remnant.", "የትሑታን መዳንና የእግዚአብሔር ደስታ በሕዝቡ።"),
        BibleBook(37, "Haggai", "ትንቢተ ሐጌ", "Hag", "ሐጌ", Testament.OLD, BookCategory.MINOR_PROPHETS, 2, "Call to rebuild God's temple and prioritize spiritual matters.", "የእግዚአብሔርን ቤት የመሥራት ጥሪ።"),
        BibleBook(38, "Zechariah", "ትንቢተ ዘካርያስ", "Zec", "ዘካ", Testament.OLD, BookCategory.MINOR_PROPHETS, 14, "Visions of Messiah: humble King on a donkey, pierced for sins.", "ስለ ንጉሡ መምጣትና ስለ መሲሑ የተነገረ ትንቢት።"),
        BibleBook(39, "Malachi", "ትንቢተ ሚልክያስ", "Mal", "ሚል", Testament.OLD, BookCategory.MINOR_PROPHETS, 4, "Covenant faithfulness, tithing, and the Sun of Righteousness.", "የጽድቅ ፀሐይ መውጣትና የቃል ኪዳን ታማኝነት።"),

        // New Testament - Gospels (ወንጌላት)
        BibleBook(40, "Matthew", "የማቴዎስ ወንጌል", "Mat", "ማቴ", Testament.NEW, BookCategory.GOSPELS, 28, "Jesus as the promised Messiah and King of the Jews.", "ኢየሱስ ክርስቶስ የተስፋው መሲሕና ንጉሥ።"),
        BibleBook(41, "Mark", "የማርቆስ ወንጌል", "Mrk", "ማር", Testament.NEW, BookCategory.GOSPELS, 16, "Jesus as the Servant who gave His life as a ransom for many.", "ኢየሱስ ክርስቶስ አገልጋይና ቤዛ።"),
        BibleBook(42, "Luke", "የሉቃስ ወንጌል", "Luk", "ሉቃ", Testament.NEW, BookCategory.GOSPELS, 24, "Jesus as the Son of Man who came to seek and save the lost.", "የጠፉትን ሊፈልግና ሊያድን የመጣው የሰው ልጅ።"),
        BibleBook(43, "John", "የዮሐንስ ወንጌል", "Jhn", "ዮሐ", Testament.NEW, BookCategory.GOSPELS, 21, "Jesus as the Son of God and the Word made flesh.", "ቃል ሥጋ ሆነ፤ በመካከላችንም አደረ፤ የሕይወት ቃል::"),

        // New Testament - Church History (የቤተ ክርስቲያን ታሪክ)
        BibleBook(44, "Acts", "የሐዋርያት ሥራ", "Act", "ሐዋ", Testament.NEW, BookCategory.CHURCH_HISTORY, 28, "The birth and rapid expansion of the Early Church by the Holy Spirit.", "በመንፈስ ቅዱስ ኃይል የወንጌል መስፋፋት።"),

        // New Testament - Epistles (መልእክታት)
        BibleBook(45, "Romans", "ወደ ሮሜ ሰዎች", "Rom", "ሮሜ", Testament.NEW, BookCategory.EPISTLES, 16, "The Gospel of God's grace, justification by faith, and sanctification.", "በእምነት ስለ መጽደቅና ስለ እግዚአብሔር ጸጋ የተጻፈ መልእክት።"),
        BibleBook(46, "1 Corinthians", "1 ወደ ቆሮንቶስ ሰዎች", "1Co", "1ቆሮ", Testament.NEW, BookCategory.EPISTLES, 16, "Order in the church, spiritual gifts, love, and the resurrection.", "መንፈሳዊ ስጦታዎች፣ ፍቅርና የሙታን ትንሣኤ።"),
        BibleBook(47, "2 Corinthians", "2 ወደ ቆሮንቶስ ሰዎች", "2Co", "2ቆሮ", Testament.NEW, BookCategory.EPISTLES, 13, "Paul's ministry, comfort in affliction, and generous giving.", "በመከራ ውስጥ ያለ መጽናናትና የሐዋርያዊ አገልግሎት።"),
        BibleBook(48, "Galatians", "ወደ ገላትያ ሰዎች", "Gal", "ገላ", Testament.NEW, BookCategory.EPISTLES, 6, "Christian freedom by grace through faith, not works of the law.", "በጸጋ የሚገኝ እውነተኛ ክርስቲያናዊ ነፃነት።"),
        BibleBook(49, "Ephesians", "ወደ ኤፌሶን ሰዎች", "Eph", "ኤፌ", Testament.NEW, BookCategory.EPISTLES, 6, "Spiritual blessings in Christ, unity in the church, and armor of God.", "በክርስቶስ ያለ መንፈሳዊ በረከት፣ አንድነትና የእግዚአብሔር የጦር ዕቃ።"),
        BibleBook(50, "Philippians", "ወደ ፊልጵስዩስ ሰዎች", "Php", "ፊል", Testament.NEW, BookCategory.EPISTLES, 4, "Rejoicing in the Lord always, humility, and contentment.", "ሁልጊዜ በጌታ ደስ ይበላችሁ፤ ክርስቶስ ሕይወቴ ነው።"),
        BibleBook(51, "Colossians", "ወደ ቆላስይስ ሰዎች", "Col", "ቆላ", Testament.NEW, BookCategory.EPISTLES, 4, "The supremacy and all-sufficiency of Christ.", "የክርስቶስ የበላይነትና ፍጹምነት።"),
        BibleBook(52, "1 Thessalonians", "1 ወደ ተሰሎንቄ ሰዎች", "1Th", "1ተሰ", Testament.NEW, BookCategory.EPISTLES, 5, "Encouragement in holiness and the Lord's return.", "በቅድስና መኖርና የጌታ ዳግም ምጽአት ተስፋ።"),
        BibleBook(53, "2 Thessalonians", "2 ወደ ተሰሎንቄ ሰዎች", "2Th", "2ተሰ", Testament.NEW, BookCategory.EPISTLES, 3, "Clarification about the Day of the Lord and patient perseverance.", "ስለ ጌታ ቀን ማብራሪያና በጽናት መቆም።"),
        BibleBook(54, "1 Timothy", "1 ወደ ጢሞቴዎስ", "1Ti", "1ጢሞ", Testament.NEW, BookCategory.EPISTLES, 6, "Pastoral leadership, sound doctrine, and godly living.", "የቤተ ክርስቲያን አመራርና ትክክለኛ ትምህርት።"),
        BibleBook(55, "2 Timothy", "2 ወደ ጢሞቴዎስ", "2Ti", "2ጢሞ", Testament.NEW, BookCategory.EPISTLES, 4, "Paul's final charge to Timothy: endure hardness and preach the Word.", "የጳውሎስ የመጨረሻ ማሳሰቢያ፡ ቃሉን ስበክ፥ በጽናት ቁም።"),
        BibleBook(56, "Titus", "ወደ ቲቶ", "Tit", "ቲቶ", Testament.NEW, BookCategory.EPISTLES, 3, "Sound doctrine, good works, and church order in Crete.", "ትክክለኛ ትምህርትና መልካም ሥራ።"),
        BibleBook(57, "Philemon", "ወደ ፊልሞና", "Phm", "ፊልሞ", Testament.NEW, BookCategory.EPISTLES, 1, "Forgiveness and Christian brotherhood in Christ.", "ይቅር ባይነትና ክርስቲያናዊ ወንድማማችነት።"),
        BibleBook(58, "Hebrews", "ወደ ዕብራውያን", "Heb", "ዕብ", Testament.NEW, BookCategory.EPISTLES, 13, "The superiority of Jesus Christ and His priesthood; hall of faith.", "የክርስቶስ ታላቅነት፣ ፍጹም ሊቀ ካህንነቱና የእምነት አርበኞች።"),
        BibleBook(59, "James", "የያዕቆብ መልእክት", "Jas", "ያዕ", Testament.NEW, BookCategory.EPISTLES, 5, "Living faith demonstrated through works, patience, and prayer.", "በሥራ የሚገለጥ ሕያው እምነትና ጸሎት።"),
        BibleBook(60, "1 Peter", "1 የጴጥሮስ መልእክት", "1Pe", "1ጴጥ", Testament.NEW, BookCategory.EPISTLES, 5, "Living hope amidst persecution and suffering.", "በመከራ ውስጥ ያለ ሕያው ተስፋና ቅድስና።"),
        BibleBook(61, "2 Peter", "2 የጴጥሮስ መልእክት", "2Pe", "2ጴጥ", Testament.NEW, BookCategory.EPISTLES, 3, "Warning against false teachers and growing in grace.", "ከሐሰተኞች መጠንቀቅና በጸጋ ማደግ።"),
        BibleBook(62, "1 John", "1 የዮሐንስ መልእክት", "1Jn", "1ዮሐ", Testament.NEW, BookCategory.EPISTLES, 5, "Fellowship with God, assurance of salvation, and love one another.", "ከእግዚአብሔር ጋር ያለው ኅብረት፣ ፍቅርና የዘላለም ሕይወት እርግጠኝነት።"),
        BibleBook(63, "2 John", "2 የዮሐንስ መልእክት", "2Jn", "2ዮሐ", Testament.NEW, BookCategory.EPISTLES, 1, "Walking in truth and guarding against deception.", "በእውነትና በፍቅር መመላለስ።"),
        BibleBook(64, "3 John", "3 የዮሐንስ መልእክት", "3Jn", "3ዮሐ", Testament.NEW, BookCategory.EPISTLES, 1, "Hospitality and supporting Christian workers.", "እንግዳ ተቀባይነትና እውነትን መደገፍ።"),
        BibleBook(65, "Jude", "የይሁዳ መልእክት", "Jud", "ይሁ", Testament.NEW, BookCategory.EPISTLES, 1, "Contending earnestly for the faith once delivered.", "ስለ ቀናች እምነት መጋደል።"),

        // New Testament - Prophecy (ራእይ)
        BibleBook(66, "Revelation", "የዮሐንስ ራእይ", "Rev", "ራእ", Testament.NEW, BookCategory.PROPHECY, 22, "The final victory of Jesus Christ, New Heaven and New Earth.", "የኢየሱስ ክርስቶስ የመጨረሻ ድል፣ አዲሱ ሰማይና አዲሲቱ ምድር።")
    )

    fun getBookById(id: Int): BibleBook? = books.find { it.id == id }
    fun getBookByName(name: String): BibleBook? = books.find {
        it.name.equals(name, ignoreCase = true) || it.amharicName.equals(name, ignoreCase = true)
    }
}

