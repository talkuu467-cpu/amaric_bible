package com.example.data

import android.content.Context
import android.util.Log
import com.example.data.model.BibleVerse
import com.example.data.model.BibleTranslation
import com.example.data.model.DailyVerse
import com.example.data.model.ReadingPlan
import com.example.data.model.ReadingPlanDay
import com.example.data.model.TopicalVerse
import org.json.JSONObject

object BibleScriptureStore {

    // Comprehensive curated scripture dataset covering key Old Testament & New Testament chapters
    private val keyVersesMap: MutableMap<String, List<BibleVerse>> = mutableMapOf()
    private val amharicKeyVersesMap: MutableMap<String, List<BibleVerse>> = mutableMapOf()
    private var isAmharicAssetsLoaded = false

    init {
        loadCoreScriptures()
        loadCoreAmharicScriptures()
    }

    private fun loadCoreScriptures() {
        // Genesis 1
        putVerses("Genesis", 1, listOf(
            "In the beginning God created the heaven and the earth.",
            "And the earth was without form, and void; and darkness was upon the face of the deep. And the Spirit of God moved upon the face of the waters.",
            "And God said, Let there be light: and there was light.",
            "And God saw the light, that it was good: and God divided the light from the darkness.",
            "And God called the light Day, and the darkness he called Night. And the evening and the morning were the first day.",
            "And God said, Let there be a firmament in the midst of the waters, and let it divide the waters from the waters.",
            "And God made the firmament, and divided the waters which were under the firmament from the waters which were above the firmament: and it was so.",
            "And God called the firmament Heaven. And the evening and the morning were the second day.",
            "And God said, Let the waters under the heaven be gathered together unto one place, and let the dry land appear: and it was so.",
            "And God called the dry land Earth; and the gathering together of the waters called he Seas: and God saw that it was good.",
            "And God said, Let the earth bring forth grass, the herb yielding seed, and the fruit tree yielding fruit after his kind, whose seed is in itself, upon the earth: and it was so.",
            "And the earth brought forth grass, and herb yielding seed after his kind, and the tree yielding fruit, whose seed was in itself, after his kind: and God saw that it was good.",
            "And the evening and the morning were the third day.",
            "And God said, Let there be lights in the firmament of the heaven to divide the day from the night; and let them be for signs, and for seasons, and for days, and years:",
            "And let them be for lights in the firmament of the heaven to give light upon the earth: and it was so.",
            "And God made two great lights; the greater light to rule the day, and the lesser light to rule the night: he made the stars also.",
            "And God set them in the firmament of the heaven to give light upon the earth,",
            "And to rule over the day and over the night, and to divide the light from the darkness: and God saw that it was good.",
            "And the evening and the morning were the fourth day.",
            "And God said, Let the waters bring forth abundantly the moving creature that hath life, and fowl that may fly above the earth in the open firmament of heaven.",
            "And God created great whales, and every living creature that moveth, which the waters brought forth abundantly, after their kind, and every winged fowl after his kind: and God saw that it was good.",
            "And God blessed them, saying, Be fruitful, and multiply, and fill the waters in the seas, and let fowl multiply in the earth.",
            "And the evening and the morning were the fifth day.",
            "And God said, Let the earth bring forth the living creature after his kind, cattle, and creeping thing, and beast of the earth after his kind: and it was so.",
            "And God made the beast of the earth after his kind, and cattle after their kind, and every thing that creepeth upon the earth after his kind: and God saw that it was good.",
            "And God said, Let us make man in our image, after our likeness: and let them have dominion over the fish of the sea, and over the fowl of the air, and over the cattle, and over all the earth, and over every creeping thing that creepeth upon the earth.",
            "So God created man in his own image, in the image of God created he him; male and female created he them.",
            "And God blessed them, and God said unto them, Be fruitful, and multiply, and replenish the earth, and subdue it: and have dominion over the fish of the sea, and over the fowl of the air, and over every living thing that moveth upon the earth.",
            "And God said, Behold, I have given you every herb bearing seed, which is upon the face of all the earth, and every tree, in the which is the fruit of a tree yielding seed; to you it shall be for meat.",
            "And to every beast of the earth, and to every fowl of the air, and to every thing that creepeth upon the earth, wherein there is life, I have given every green herb for meat: and it was so.",
            "And God saw every thing that he had made, and, behold, it was very good. And the evening and the morning were the sixth day."
        ))

        // Exodus 20 - Ten Commandments
        putVerses("Exodus", 20, listOf(
            "And God spake all these words, saying,",
            "I am the LORD thy God, which have brought thee out of the land of Egypt, out of the house of bondage.",
            "Thou shalt have no other gods before me.",
            "Thou shalt not make unto thee any graven image, or any likeness of any thing that is in heaven above, or that is in the earth beneath, or that is in the water under the earth.",
            "Thou shalt not bow down thyself to them, nor serve them: for I the LORD thy God am a jealous God, visiting the iniquity of the fathers upon the children unto the third and fourth generation of them that hate me;",
            "And shewing mercy unto thousands of them that love me, and keep my commandments.",
            "Thou shalt not take the name of the LORD thy God in vain; for the LORD will not hold him guiltless that taketh his name in vain.",
            "Remember the sabbath day, to keep it holy.",
            "Six days shalt thou labour, and do all thy work:",
            "But the seventh day is the sabbath of the LORD thy God: in it thou shalt not do any work, thou, nor thy son, nor thy daughter, thy manservant, nor thy maidservant, nor thy cattle, nor thy stranger that is within thy gates:",
            "For in six days the LORD made heaven and earth, the sea, and all that in them is, and rested the seventh day: wherefore the LORD blessed the sabbath day, and hallowed it.",
            "Honour thy father and thy mother: that thy days may be long upon the land which the LORD thy God giveth thee.",
            "Thou shalt not kill.",
            "Thou shalt not commit adultery.",
            "Thou shalt not steal.",
            "Thou shalt not bear false witness against thy neighbour.",
            "Thou shalt not covet thy neighbour's house, thou shalt not covet thy neighbour's wife, nor his manservant, nor his maidservant, nor his ox, nor his ass, nor any thing that is thy neighbour's."
        ))

        // Psalm 1
        putVerses("Psalms", 1, listOf(
            "Blessed is the man that walketh not in the counsel of the ungodly, nor standeth in the way of sinners, nor sitteth in the seat of the scornful.",
            "But his delight is in the law of the LORD; and in his law doth he meditate day and night.",
            "And he shall be like a tree planted by the rivers of water, that bringeth forth his fruit in his season; his leaf also shall not wither; and whatsoever he doeth shall prosper.",
            "The ungodly are not so: but are like the chaff which the wind driveth away.",
            "Therefore the ungodly shall not stand in the judgment, nor sinners in the congregation of the righteous.",
            "For the LORD knoweth the way of the righteous: but the way of the ungodly shall perish."
        ))

        // Psalm 23
        putVerses("Psalms", 23, listOf(
            "The LORD is my shepherd; I shall not want.",
            "He maketh me to lie down in green pastures: he leadeth me beside the still waters.",
            "He restoreth my soul: he leadeth me in the paths of righteousness for his name's sake.",
            "Yea, though I walk through the valley of the shadow of death, I will fear no evil: for thou art with me; thy rod and thy staff they comfort me.",
            "Thou preparest a table before me in the presence of mine enemies: thou anointest my head with oil; my cup runneth over.",
            "Surely goodness and mercy shall follow me all the days of my life: and I will dwell in the house of the LORD for ever."
        ))

        // Psalm 91
        putVerses("Psalms", 91, listOf(
            "He that dwelleth in the secret place of the most High shall abide under the shadow of the Almighty.",
            "I will say of the LORD, He is my refuge and my fortress: my God; in him will I trust.",
            "Surely he shall deliver thee from the snare of the fowler, and from the noisome pestilence.",
            "He shall cover thee with his feathers, and under his wings shalt thou trust: his truth shall be thy shield and buckler.",
            "Thou shalt not be afraid for the terror by night; nor for the arrow that flieth by day;",
            "Nor for the pestilence that walketh in darkness; nor for the destruction that wasteth at noonday.",
            "A thousand shall fall at thy side, and ten thousand at thy right hand; but it shall not come nigh thee.",
            "Only with thine eyes shalt thou behold and see the reward of the wicked.",
            "Because thou hast made the LORD, which is my refuge, even the most High, thy habitation;",
            "There shall no evil befall thee, neither shall any plague come nigh thy dwelling.",
            "For he shall give his angels charge over thee, to keep thee in all thy ways.",
            "They shall bear thee up in their hands, lest thou dash thy foot against a stone.",
            "Thou shalt tread upon the lion and adder: the young lion and the dragon shalt thou trample under feet.",
            "Because he hath set his love upon me, therefore will I deliver him: I will set him on high, because he hath known my name.",
            "He shall call upon me, and I will answer him: I will be with him in trouble; I will deliver him, and honour him.",
            "With long life will I satisfy him, and shew him my salvation."
        ))

        // Psalm 100
        putVerses("Psalms", 100, listOf(
            "Make a joyful noise unto the LORD, all ye lands.",
            "Serve the LORD with gladness: come before his presence with singing.",
            "Know ye that the LORD he is God: it is he that hath made us, and not we ourselves; we are his people, and the sheep of his pasture.",
            "Enter into his gates with thanksgiving, and into his courts with praise: be thankful unto him, and bless his name.",
            "For the LORD is good; his mercy is everlasting; and his truth endureth to all generations."
        ))

        // Psalm 121
        putVerses("Psalms", 121, listOf(
            "I will lift up mine eyes unto the hills, from whence cometh my help.",
            "My help cometh from the LORD, which made heaven and earth.",
            "He will not suffer thy foot to be moved: he that keepeth thee will not slumber.",
            "Behold, he that keepeth Israel shall neither slumber nor sleep.",
            "The LORD is thy keeper: the LORD is thy shade upon thy right hand.",
            "The sun shall not smite thee by day, nor the moon by night.",
            "The LORD shall preserve thee from all evil: he shall preserve thy soul.",
            "The LORD shall preserve thy going out and thy coming in from this time forth, and even for evermore."
        ))

        // Psalm 139
        putVerses("Psalms", 139, listOf(
            "O LORD, thou hast searched me, and known me.",
            "Thou knowest my downsitting and mine uprising, thou understandest my thought afar off.",
            "Thou compassest my path and my lying down, and art acquainted with all my ways.",
            "For there is not a word in my tongue, but, lo, O LORD, thou knowest it altogether.",
            "Thou hast beset me behind and before, and laid thine hand upon me.",
            "Such knowledge is too wonderful for me; it is high, I cannot attain unto it.",
            "Whither shall I go from thy spirit? or whither shall I flee from thy presence?",
            "If I ascend up into heaven, thou art there: if I make my bed in hell, behold, thou art there.",
            "If I take the wings of the morning, and dwell in the uttermost parts of the sea;",
            "Even there shall thy hand lead me, and thy right hand shall hold me.",
            "If I say, Surely the darkness shall cover me; even the night shall be light about me.",
            "Yea, the darkness hideth not from thee; but the night shineth as the day: the darkness and the light are both alike to thee.",
            "For thou hast possessed my reins: thou hast covered me in my mother's womb.",
            "I will praise thee; for I am fearfully and wonderfully made: marvellous are thy works; and that my soul knoweth right well."
        ))

        // Proverbs 3
        putVerses("Proverbs", 3, listOf(
            "My son, forget not my law; but let thine heart keep my commandments:",
            "For length of days, and long life, and peace, shall they add to thee.",
            "Let not mercy and truth forsake thee: bind them about thy neck; write them upon the table of thine heart:",
            "So shalt thou find favour and good understanding in the sight of God and man.",
            "Trust in the LORD with all thine heart; and lean not unto thine own understanding.",
            "In all thy ways acknowledge him, and he shall direct thy paths.",
            "Be not wise in thine own eyes: fear the LORD, and depart from evil.",
            "It shall be health to thy navel, and marrow to thy bones.",
            "Honour the LORD with thy substance, and with the firstfruits of all thine increase:",
            "So shall thy barns be filled with plenty, and thy presses shall burst out with new wine.",
            "My son, despise not the chastening of the LORD; neither be weary of his correction:",
            "For whom the LORD loveth he correcteth; even as a father the son in whom he delighteth.",
            "Happy is the man that findeth wisdom, and the man that getteth understanding."
        ))

        // Isaiah 40
        putVerses("Isaiah", 40, listOf(
            "Comfort ye, comfort ye my people, saith your God.",
            "Speak ye comfortably to Jerusalem, and cry unto her, that her warfare is accomplished, that her iniquity is pardoned:",
            "The voice of him that crieth in the wilderness, Prepare ye the way of the LORD, make straight in the desert a highway for our God.",
            "Every valley shall be exalted, and every mountain and hill shall be made low: and the crooked shall be made straight, and the rough places plain:",
            "And the glory of the LORD shall be revealed, and all flesh shall see it together: for the mouth of the LORD hath spoken it.",
            "The voice said, Cry. And he said, What shall I cry? All flesh is grass, and all the goodliness thereof is as the flower of the field:",
            "The grass withereth, the flower fadeth: because the spirit of the LORD bloweth upon it: surely the people is grass.",
            "The grass withereth, the flower fadeth: but the word of our God shall stand for ever.",
            "Hast thou not known? hast thou not heard, that the everlasting God, the LORD, the Creator of the ends of the earth, fainteth not, neither is weary? there is no searching of his understanding.",
            "He giveth power to the faint; and to them that have no might he increaseth strength.",
            "Even the youths shall faint and be weary, and the young men shall utterly fall:",
            "But they that wait upon the LORD shall renew their strength; they shall mount up with wings as eagles; they shall run, and not be weary; and they shall walk, and not faint."
        ))

        // Isaiah 53
        putVerses("Isaiah", 53, listOf(
            "Who hath believed our report? and to whom is the arm of the LORD revealed?",
            "For he shall grow up before him as a tender plant, and as a root out of a dry ground: he hath no form nor comeliness; and when we shall see him, there is no beauty that we should desire him.",
            "He is despised and rejected of men; a man of sorrows, and acquainted with grief: and we hid as it were our faces from him; he was despised, and we esteemed him not.",
            "Surely he hath borne our griefs, and carried our sorrows: yet we did esteem him stricken, smitten of God, and afflicted.",
            "But he was wounded for our transgressions, he was bruised for our iniquities: the chastisement of our peace was upon him; and with his stripes we are healed.",
            "All we like sheep have gone astray; we have turned every one to his own way; and the LORD hath laid on him the iniquity of us all.",
            "He was oppressed, and he was afflicted, yet he opened not his mouth: he is brought as a lamb to the slaughter, and as a sheep before her shearers is dumb, so he openeth not his mouth."
        ))

        // Matthew 5 - Beatitudes
        putVerses("Matthew", 5, listOf(
            "And seeing the multitudes, he went up into a mountain: and when he was set, his disciples came unto him:",
            "And he opened his mouth, and taught them, saying,",
            "Blessed are the poor in spirit: for theirs is the kingdom of heaven.",
            "Blessed are they that mourn: for they shall be comforted.",
            "Blessed are the meek: for they shall inherit the earth.",
            "Blessed are they which do hunger and thirst after righteousness: for they shall be filled.",
            "Blessed are the merciful: for they shall obtain mercy.",
            "Blessed are the pure in heart: for they shall see God.",
            "Blessed are the peacemakers: for they shall be called the children of God.",
            "Blessed are they which are persecuted for righteousness' sake: for theirs is the kingdom of heaven.",
            "Blessed are ye, when men shall revile you, and persecute you, and shall say all manner of evil against you falsely, for my sake.",
            "Rejoice, and be exceeding glad: for great is your reward in heaven: for so persecuted they the prophets which were before you.",
            "Ye are the salt of the earth: but if the salt have lost his savour, wherewith shall it be salted? it is thenceforth good for nothing, but to be cast out, and to be trodden under foot of men.",
            "Ye are the light of the world. A city that is set on an hill cannot be hid.",
            "Neither do men light a candle, and put it under a bushel, but on a candlestick; and it giveth light unto all that are in the house.",
            "Let your light so shine before men, that they may see your good works, and glorify your Father which is in heaven."
        ))

        // Matthew 6 - The Lord's Prayer & Trust
        putVerses("Matthew", 6, listOf(
            "Take heed that ye do not your alms before men, to be seen of them: otherwise ye have no reward of your Father which is in heaven.",
            "Therefore when thou doest thine alms, do not sound a trumpet before thee, as the hypocrites do in the synagogues and in the streets, that they may have glory of men. Verily I say unto you, They have their reward.",
            "But when thou doest alms, let not thy left hand know what thy right hand doeth:",
            "That thine alms may be in secret: and thy Father which seeth in secret himself shall reward thee openly.",
            "And when thou prayest, thou shalt not be as the hypocrites are: for they love to pray standing in the synagogues and in the corners of the streets, that they may be seen of men. Verily I say unto you, They have their reward.",
            "But thou, when thou prayest, enter into thy closet, and when thou hast shut thy door, pray to thy Father which is in secret; and thy Father which seeth in secret shall reward thee openly.",
            "But when ye pray, use not vain repetitions, as the heathen do: for they think that they shall be heard for their much speaking.",
            "Be not ye therefore like unto them: for your Father knoweth what things ye have need of, before ye ask him.",
            "After this manner therefore pray ye: Our Father which art in heaven, Hallowed be thy name.",
            "Thy kingdom come. Thy will be done in earth, as it is in heaven.",
            "Give us this day our daily bread.",
            "And forgive us our debts, as we forgive our debtors.",
            "And lead us not into temptation, but deliver us from evil: For thine is the kingdom, and the power, and the glory, for ever. Amen.",
            "For if ye forgive men their trespasses, your heavenly Father will also forgive you:",
            "But if ye forgive not men their trespasses, neither will your Father forgive your trespasses.",
            "Therefore I say unto you, Take no thought for your life, what ye shall eat, or what ye shall drink; nor yet for your body, what ye shall put on. Is not the life more than meat, and the body than raiment?",
            "Behold the fowls of the air: for they sow not, neither do they reap, nor gather into barns; yet your heavenly Father feedeth them. Are ye not much better than they?",
            "Which of you by taking thought can add one cubit unto his stature?",
            "And why take ye thought for raiment? Consider the lilies of the field, how they grow; they toil not, neither do they spin:",
            "And yet I say unto you, That even Solomon in all his glory was not arrayed like one of these.",
            "Wherefore, if God so clothe the grass of the field, which to day is, and to morrow is cast into the oven, shall he not much more clothe you, O ye of little faith?",
            "Therefore take no thought, saying, What shall we eat? or, What shall we drink? or, Wherewithal shall we be clothed?",
            "For after all these things do the Gentiles seek: for your heavenly Father knoweth that ye have need of all these things.",
            "But seek ye first the kingdom of God, and his righteousness; and all these things shall be added unto you.",
            "Take therefore no thought for the morrow: for the morrow shall take thought for the things of itself. Sufficient unto the day is the evil thereof."
        ))

        // John 1
        putVerses("John", 1, listOf(
            "In the beginning was the Word, and the Word was with God, and the Word was God.",
            "The same was in the beginning with God.",
            "All things were made by him; and without him was not any thing made that was made.",
            "In him was life; and the life was the light of men.",
            "And the light shineth in darkness; and the darkness comprehended it not.",
            "There was a man sent from God, whose name was John.",
            "The same came for a witness, to bear witness of the Light, that all men through him might believe.",
            "He was not that Light, but was sent to bear witness of that Light.",
            "That was the true Light, which lighteth every man that cometh into the world.",
            "He was in the world, and the world was made by him, and the world knew him not.",
            "He came unto his own, and his own received him not.",
            "But as many as received him, to them gave he power to become the sons of God, even to them that believe on his name:",
            "Which were born, not of blood, nor of the will of the flesh, nor of the will of man, but of God.",
            "And the Word was made flesh, and dwelt among us, (and we beheld his glory, the glory as of the only begotten of the Father,) full of grace and truth."
        ))

        // John 3
        putVerses("John", 3, listOf(
            "There was a man of the Pharisees, named Nicodemus, a ruler of the Jews:",
            "The same came to Jesus by night, and said unto him, Rabbi, we know that thou art a teacher come from God: for no man can do these miracles that thou doest, except God be with him.",
            "Jesus answered and said unto him, Verily, verily, I say unto thee, Except a man be born again, he cannot see the kingdom of God.",
            "Nicodemus saith unto him, How can a man be born when he is old? can he enter the second time into his mother's womb, and be born?",
            "Jesus answered, Verily, verily, I say unto thee, Except a man be born of water and of the Spirit, he cannot enter into the kingdom of God.",
            "That which is born of the flesh is flesh; and that which is born of the Spirit is spirit.",
            "Marvel not that I said unto thee, Ye must be born again.",
            "The wind bloweth where it listeth, and thou hearest the sound thereof, but canst not tell whence it cometh, and whither it goeth: so is every one that is born of the Spirit.",
            "For God so loved the world, that he gave his only begotten Son, that whosoever believeth in him should not perish, but have everlasting life.",
            "For God sent not his Son into the world to condemn the world; but that the world through him might be saved.",
            "He that believeth on him is not condemned: but he that believeth not is condemned already, because he hath not believed in the name of the only begotten Son of God.",
            "And this is the condemnation, that light is come into the world, and men loved darkness rather than light, because their deeds were evil."
        ))

        // John 14
        putVerses("John", 14, listOf(
            "Let not your heart be troubled: ye believe in God, believe also in me.",
            "In my Father's house are many mansions: if it were not so, I would have told you. I go to prepare a place for you.",
            "And if I go and prepare a place for you, I will come again, and receive you unto myself; that where I am, there ye may be also.",
            "And whither I go ye know, and the way ye know.",
            "Thomas saith unto him, Lord, we know not whither thou goest; and how can we know the way?",
            "Jesus saith unto him, I am the way, the truth, and the life: no man cometh unto the Father, but by me.",
            "If ye had known me, ye should have known my Father also: and from henceforth ye know him, and have seen him.",
            "Peace I leave with you, my peace I give unto you: not as the world giveth, give I unto you. Let not your heart be troubled, neither let it be afraid.",
            "Ye have heard how I said unto you, I go away, and come again unto you. If ye loved me, ye would rejoice, because I said, I go unto the Father: for my Father is greater than I."
        ))

        // Romans 8
        putVerses("Romans", 8, listOf(
            "There is therefore now no condemnation to them which are in Christ Jesus, who walk not after the flesh, but after the Spirit.",
            "For the law of the Spirit of life in Christ Jesus hath made me free from the law of sin and death.",
            "For what the law could not do, in that it was weak through the flesh, God sending his own Son in the likeness of sinful flesh, and for sin, condemned sin in the flesh:",
            "That the righteousness of the law might be fulfilled in us, who walk not after the flesh, but after the Spirit.",
            "For they that are after the flesh do mind the things of the flesh; but they that are after the Spirit the things of the Spirit.",
            "For to be carnally minded is death; but to be spiritually minded is life and peace.",
            "The Spirit itself beareth witness with our spirit, that we are the children of God:",
            "And if children, then heirs; heirs of God, and joint-heirs with Christ; if so be that we suffer with him, that we may be also glorified together.",
            "For I reckon that the sufferings of this present time are not worthy to be compared with the glory which shall be revealed in us.",
            "And we know that all things work together for good to them that love God, to them who are the called according to his purpose.",
            "For whom he did foreknow, he also did predestinate to be conformed to the image of his Son, that he might be the firstborn among many brethren.",
            "What shall we then say to these things? If God be for us, who can be against us?",
            "He that spared not his own Son, but delivered him up for us all, how shall he not with him also freely give us all things?",
            "Who shall separate us from the love of Christ? shall tribulation, or distress, or persecution, or famine, or nakedness, or peril, or sword?",
            "Nay, in all these things we are more than conquerors through him that loved us.",
            "For I am persuaded, that neither death, nor life, nor angels, nor principalities, nor powers, nor things present, nor things to come,",
            "Nor height, nor depth, nor any other creature, shall be able to separate us from the love of God, which is in Christ Jesus our Lord."
        ))

        // 1 Corinthians 13 - Love Chapter
        putVerses("1 Corinthians", 13, listOf(
            "Though I speak with the tongues of men and of angels, and have not charity, I am become as sounding brass, or a tinkling cymbal.",
            "And though I have the gift of prophecy, and understand all mysteries, and all knowledge; and though I have all faith, so that I could remove mountains, and have not charity, I am nothing.",
            "And though I bestow all my goods to feed the poor, and though I give my body to be burned, and have not charity, it profiteth me nothing.",
            "Charity suffereth long, and is kind; charity envieth not; charity vaunteth not itself, is not puffed up,",
            "Doth not behave itself unseemly, seeketh not her own, is not easily provoked, thinketh no evil;",
            "Rejoiceth not in iniquity, but rejoiceth in the truth;",
            "Beareth all things, believeth all things, hopeth all things, endureth all things.",
            "Charity never faileth: but whether there be prophecies, they shall fail; whether there be tongues, they shall cease; whether there be knowledge, it shall vanish away.",
            "For we know in part, and we prophesy in part.",
            "But when that which is perfect is come, then that which is in part shall be done away.",
            "When I was a child, I spake as a child, I understood as a child, I thought as a child: but when I became a man, I put away childish things.",
            "For now we see through a glass, darkly; but then face to face: now I know in part; but then shall I know even as also I am known.",
            "And now abideth faith, hope, charity, these three; but the greatest of these is charity."
        ))

        // Ephesians 6 - Armor of God
        putVerses("Ephesians", 6, listOf(
            "Children, obey your parents in the Lord: for this is right.",
            "Honour thy father and mother; which is the first commandment with promise;",
            "That it may be well with thee, and thou mayest live long on the earth.",
            "And, ye fathers, provoke not your children to wrath: but bring them up in the nurture and admonition of the Lord.",
            "Finally, my brethren, be strong in the Lord, and in the power of his might.",
            "Put on the whole armour of God, that ye may be able to stand against the wiles of the devil.",
            "For we wrestle not against flesh and blood, but against principalities, against powers, against the rulers of the darkness of this world, against spiritual wickedness in high places.",
            "Wherefore take unto you the whole armour of God, that ye may be able to withstand in the evil day, and having done all, to stand.",
            "Stand therefore, having your loins girt about with truth, and having on the breastplate of righteousness;",
            "And your feet shod with the preparation of the gospel of peace;",
            "Above all, taking the shield of faith, wherewith ye shall be able to quench all the fiery darts of the wicked.",
            "And take the helmet of salvation, and the sword of the Spirit, which is the word of God:",
            "Praying always with all prayer and supplication in the Spirit, and watching thereunto with all perseverance and supplication for all saints;"
        ))

        // Philippians 4
        putVerses("Philippians", 4, listOf(
            "Therefore, my brethren dearly beloved and longed for, my joy and crown, so stand fast in the Lord, my dearly beloved.",
            "Rejoice in the Lord alway: and again I say, Rejoice.",
            "Let your moderation be known unto all men. The Lord is at hand.",
            "Be careful for nothing; but in every thing by prayer and supplication with thanksgiving let your requests be made known unto God.",
            "And the peace of God, which passeth all understanding, shall keep your hearts and minds through Christ Jesus.",
            "Finally, brethren, whatsoever things are true, whatsoever things are honest, whatsoever things are just, whatsoever things are pure, whatsoever things are lovely, whatsoever things are of good report; if there be any virtue, and if there be any praise, think on these things.",
            "Those things, which ye have both learned, and received, and heard, and seen in me, do: and the God of peace shall be with you.",
            "Not that I speak in respect of want: for I have learned, in whatsoever state I am, therewith to be content.",
            "I know both how to be abased, and I know how to abound: every where and in all things I am instructed both to be full and to be hungry, both to abound and to suffer need.",
            "I can do all things through Christ which strengtheneth me.",
            "Notwithstanding ye have well done, that ye did communicate with my affliction.",
            "But my God shall supply all your need according to his riches in glory by Christ Jesus.",
            "Now unto God and our Father be glory for ever and ever. Amen."
        ))

        // Hebrews 11 - Faith Hall of Fame
        putVerses("Hebrews", 11, listOf(
            "Now faith is the substance of things hoped for, the evidence of things not seen.",
            "For by it the elders obtained a good report.",
            "Through faith we understand that the worlds were framed by the word of God, so that things which are seen were not made of things which do appear.",
            "By faith Abel offered unto God a more excellent sacrifice than Cain, by which he obtained witness that he was righteous, God testifying of his gifts: and by it he being dead yet speaketh.",
            "By faith Enoch was translated that he should not see death; and was not found, because God had translated him: for before his translation he had this testimony, that he pleased God.",
            "But without faith it is impossible to please him: for he that cometh to God must believe that he is, and that he is a rewarder of them that diligently seek him.",
            "By faith Noah, being warned of God of things not seen as yet, moved with fear, prepared an ark to the saving of his house; by the which he condemned the world, and became heir of the righteousness which is by faith.",
            "By faith Abraham, when he was called to go out into a place which he should after receive for an inheritance, obeyed; and he went out, not knowing whither he went."
        ))

        // James 1
        putVerses("James", 1, listOf(
            "James, a servant of God and of the Lord Jesus Christ, to the twelve tribes which are scattered abroad, greeting.",
            "My brethren, count it all joy when ye fall into divers temptations;",
            "Knowing this, that the trying of your faith worketh patience.",
            "But let patience have her perfect work, that ye may be perfect and entire, wanting nothing.",
            "If any of you lack wisdom, let him ask of God, that giveth to all men liberally, and upbraideth not; and it shall be given him.",
            "But let him ask in faith, nothing wavering. For he that wavereth is like a wave of the sea driven with the wind and tossed.",
            "Every good gift and every perfect gift is from above, and cometh down from the Father of lights, with whom is no variableness, neither shadow of turning.",
            "Wherefore, my beloved brethren, let every man be swift to hear, slow to speak, slow to wrath:",
            "For the wrath of man worketh not the righteousness of God.",
            "But be ye doers of the word, and not hearers only, deceiving your own selves."
        ))

        // Revelation 21
        putVerses("Revelation", 21, listOf(
            "And I saw a new heaven and a new earth: for the first heaven and the first earth were passed away; and there was no more sea.",
            "And I John saw the holy city, new Jerusalem, coming down from God out of heaven, prepared as a bride adorned for her husband.",
            "And I heard a great voice out of heaven saying, Behold, the tabernacle of God is with men, and he will dwell with them, and they shall be his people, and God himself shall be with them, and be their God.",
            "And God shall wipe away all tears from their eyes; and there shall be no more death, neither sorrow, nor crying, neither shall there be any more pain: for the former things are passed away.",
            "And he that sat upon the throne said, Behold, I make all things new. And he said unto me, Write: for these words are true and faithful.",
            "And he said unto me, It is done. I am Alpha and Omega, the beginning and the end. I will give unto him that is athirst of the fountain of the water of life freely.",
            "He that overcometh shall inherit all things; and I will be his God, and he shall be my son."
        ))
    }

    private fun loadCoreAmharicScriptures() {
        // Psalm 23 (መዝሙረ ዳዊት 23)
        putAmharicVerses(19, 23, listOf(
            "እግዚአብሔር እረኛዬ ነው፥ የሚያሳጣኝም የለም።",
            "በለመለመ መስክ ያሳድረኛል፤ በዕረፍት ውኃ ዘንድ ይመራኛል።",
            "ነፍሴን መለሳት፥ ስለ ስሙም በጽድቅ መንገድ መራኝ።",
            "በሞት ጥላ መካከል እንኳ ብሄድ አንተ ከእኔ ጋር ነህና ክፉን አልፈራም፤ በትረህና ምርኵዝህ እነርሱ ያጸናኑኛል።",
            "በፊቴ ገበታን አዘጋጀህልኝ በጠላቶቼ ፊት ለፊት፤ ራሴን በዘይት ቀባህ፥ ጽዋዬም የተረፈ ነው።",
            "ቸርነትህና ምሕረትህ በሕይወቴ ዘመን ሁሉ ይከተሉኛል፥ በእግዚአብሔርም ቤት ለዘላለም እኖራለሁ።"
        ))

        // Psalm 1 (መዝሙረ ዳዊት 1)
        putAmharicVerses(19, 1, listOf(
            "በክፉዎች ምክር ያልሄደ፥ በኃጢአተኞችም መንገድ ያልቆመ፥ በዋዘኞችም ወንበር ያልተቀመጠ ሰው ምስጉን ነው።",
            "ነገር ግን በእግዚአብሔር ሕግ ደስ ይለዋል፥ ሕጉንም በቀንም በሌሊትም ያሰላስላል።",
            "እርሱም በውኃ ፈሳሾች ዳር እንደ ተተከለች፥ ፍሬዋን በየጊዜዋ እንደምትሰጥ፥ ቅጠልዋም እንደማይረግፍ ዛፍ ይሆናል፤ የሚሠራውም ሁሉ ይከናወንለታል።",
            "ክፉዎች እንዲህ አይደሉም፥ ነገር ግን ነፋስ ጠርጎ እንደሚወስደው ገለባ ናቸው።",
            "ስለዚህ ክፉዎች በፍርድ፥ ኃጢአተኞችም በጻድቃን ማኅበር አይቆሙም።",
            "እግዚአብሔር የጻድቃንን መንገድ ያውቃልና፥ የክፉዎች መንገድ ግን ትጠፋለች።"
        ))

        // Psalm 91 (መዝሙረ ዳዊት 91)
        putAmharicVerses(19, 91, listOf(
            "በልዑል መጠጊያ የሚኖር ሁሉን በሚችል አምላክ ጥላ ውስጥ ያድራል።",
            "እግዚአብሔርን። አንተ መታመኛዬና አምባዬ ነህ፥ የምታመንብህ አምላኬ እለዋለሁ።",
            "እርሱ ከአዳኝ ወጥመድ ከሚያጠፋም ቸነፈር ያድንሃልና።",
            "በላባዎቹ ይጋርድሃል፥ በክንፎቹም በታች ትጠጋለህ፤ እውነቱ እንደ ጋሻና እንደ መከታ ይከብብሃል።",
            "ከሌሊት ፍርሃት፥ በቀን ከሚበርር ፍላጻ፥",
            "በጨለማ ከሚሄድ ቸነፈር፥ ከቀትር ሸክፍና ከሚያጠፋ አጋንንት አትፈራም።",
            "በአጠገብህ ሺህ፥ በቀኝህም አሥር ሺህ ይወድቃሉ፤ ወደ አንተ ግን አይቀርብም።",
            "በዓይኖችህ ብቻ ትመለከታለህ፥ የክፉዎችንም ብድራት ታያለህ።",
            "አቤቱ፥ አንተ መታመኛዬ ነህና፤ ልዑልን መጠጊያህ አደረግህ።",
            "ክፉ ነገር ወደ አንተ አይቀርብም፥ መቅሠፍትም ወደ ድንኳንህ አይገባም፤",
            "በመንገድህ ሁሉ ይጠብቁህ ዘንድ መላእክቱን ስለ አንተ ያዝዛቸዋልና፤",
            "እግርህም በድንጋይ እንዳትሰናከል በእጆቻቸው ያነሡሃል።",
            "በተኵላና በእባብ ላይ ትረግጣለህ፤ አንበሳውንና ዘንዶውን ትረግጣቸዋለህ።",
            "በእኔ ተማምኖአልና አስጥለዋለሁ፤ ስሜንም አውቆአልና እጋርደዋለሁ።",
            "ይጠራኛል እመልስለትማለሁ፥ በመከራውም ጊዜ ከእርሱ ጋር እሆናለሁ፤ አድነዋለሁ አከብረውማለሁ።",
            "ረጅም ዕድሜን አጠግበዋለሁ፥ ማዳኔንም አሳየዋለሁ።"
        ))

        // Psalm 100 (መዝሙረ ዳዊት 100)
        putAmharicVerses(19, 100, listOf(
            "ምድር ሁሉ፥ ለእግዚአብሔር እልል በሉ፤ በደስታም ለእግዚአብሔር ተገዙ፥ በደስታም ወደ ፊቱ ግቡ።",
            "እግዚአብሔር እርሱ አምላክ እንደ ሆነ እወቁ፤ እርሱ ፈጠረን እኛም የእርሱ ነን፥ እኛስ ሕዝቡ የማሰማሪያውም በጎች ነን።",
            "ወደ ደጆቹ በምስጋና፥ ወደ አደባባዮቹም በውዳሴ ግቡ፤ አመስግኑት፥ ስሙንም ባርኩ።",
            "እግዚአብሔር ቸር ነውና፥ ምሕረቱም ለዘላለም ነውና፥ እውነቱም ለልጅ ልጅ ነውና።"
        ))

        // Psalm 121 (መዝሙረ ዳዊት 121)
        putAmharicVerses(19, 121, listOf(
            "ዓይኖቼን ወደ ተራሮች አነሣሁ፤ ረዳቴ ከወዴት ይምጣ?",
            "ረዳቴ ሰማይንና ምድርን ከሠራ ከእግዚአብሔር ዘንድ ነው።",
            "እግርህን ለመናወጥ አይሰጠውም፥ የሚጠብቅህም አይተኛም።",
            "እነሆ፥ እስራኤልን የሚጠብቅ አይተኛም አያንቀላፋምም።",
            "እግዚአብሔር ይጠብቅሃል፥ እግዚአብሔር በቀኝ እጅህ በኩል ጥላህ ነው።",
            "ፀሐይ በቀን አያቃጥልህም፥ ጨረቃም በሌሊት።",
            "እግዚአብሔር ከክፉ ሁሉ ይጠብቅሃል፥ ነፍስህንም ይጠብቃታል።",
            "ከዛሬ ጀምሮ እስከ ዘላለም ድረስ እግዚአብሔር መውጣትህንና መግባትህን ይጠብቃል።"
        ))

        // Psalm 139 (መዝሙረ ዳዊት 139)
        putAmharicVerses(19, 139, listOf(
            "አቤቱ፥ መረመርኸኝ አወቅኸኝም። አንተ መቀመጤንና መነሣቴን አወቅህ፤ አሳቤን ሁሉ ከሩቅ አስተዋልህ።",
            "ፍለጋዬንና መተኛቴን መረመርህ፥ መንገዶቼንም ሁሉ አወቅህ።",
            "የአንደበቴ ቃል ገና ሳይወጣ፥ እነሆ፥ አቤቱ፥ አንተ ሁሉን አወቅህ።",
            "አንተ ከበስተ ኋላዬና ከበስተ ፊቴ ከበብኸኝ፥ እጅህንም በላዬ አደረግህ።",
            "ዕውቀትህ ከእኔ ይልቅ ድንቅ ሆነችብኝ፤ በረታችብኝ፥ ልደርስባትም አልችልም።",
            "ከመንፈስህ ወዴት እሄዳለሁ? ከፊትህስ ወዴት እሸሻለሁ?",
            "ወደ ሰማይ ብወጣ፥ አንተ በዚያ አለህ፤ ወደ ሲኦልም ብወርድ፥ እነሆ፥ በዚያ አለህ።",
            "የማለዳን ክንፍ ብወስድ፥ እስከ ባሕር ዳርቻም ብበር፥",
            "በዚያ ደግሞ እጅህ ትመራኛለች፥ ቀኝህም ትይዘኛለች።"
        ))

        // Proverbs 3 (መጽሐፈ ምሳሌ 3)
        putAmharicVerses(20, 3, listOf(
            "ልጄ ሆይ፥ ሕጌን አትርሳ፥ ልብህም ትእዛዛቴን ይጠብቅ፤",
            "ብዙ ዘመናትና ረጅም ዕድሜ ሰላምም ይጨመሩልሃልና።",
            "ምሕረትና እውነት ከአንተ አይራቁ፤ በአንገትህ እሰራቸው፥ በልብህ ጽላት ላይ ጻፋቸው፤",
            "በእግዚአብሔርና በሰው ፊት ሞገስንና መልካም ማስተዋልን ታገኛለህ።",
            "በፍጹም ልብህ በእግዚአብሔር ታመን፥ በራስህም ማስተዋል አትደገፍ፤",
            "በመንገድህ ሁሉ እርሱን እወቅ፥ እርሱም ጎዳናህን ያቀናልሃል።",
            "በራስህ ዓይን ጠቢብ አትሁን፤ እግዚአብሔርን ፍራ፥ ከክፉም ራቅ፤",
            "ለሥጋህ ፈውስ፥ ለአጥንትህም ቅልጥም ይሆናልና።"
        ))

        // Isaiah 40 (ትንቢተ ኢሳይያስ 40)
        putAmharicVerses(23, 40, listOf(
            "አጽናኑ፥ ሕዝቤን አጽናኑ ይላል አምላካችሁ።",
            "ለኢየሩሳሌም ልብ ተናገሩ፥ የተቀጠረላት ወራት እንደ ተፈጸመ፥ በደልዋም እንደ ተሰረየላት፥ ከእግዚአብሔርም እጅ ስለ ኃጢአትዋ ሁሉ ሁለት እጥፍ እንደ ተቀበለች ወደ እርስዋ ጩኹ።",
            "የአዋጅ ነጋሪ ቃል፦ የእግዚአብሔርን መንገድ በምድረ በዳ ጥረጉ፥ ለአምላካችንም ጎዳና በበረሀ አስተካክሉ።",
            "ሸለቆው ሁሉ ከፍ ይበል፥ ተራራውና ኮረብታውም ሁሉ ዝቅ ይበል፤ ጠማማውም ይቅና፥ ሸካራውም ሜዳ ይሁን፤",
            "የእግዚአብሔርም ክብር ይገለጣል፥ ሥጋ ያለውም ሁሉ በአንድነት ያየዋል፥ የእግዚአብሔር አፍ ይህን ተናግሮአልና።",
            "እግዚአብሔርን በመተማመን የሚጠባበቁ ግን ኃይላቸውን ያድሳሉ፤ እንደ ንስር በክንፍ ይወጣሉ፤ ይሮጣሉ፥ አይታክቱም፤ ይሄዳሉ፥ አይደክሙም።"
        ))

        // Isaiah 53 (ትንቢተ ኢሳይያስ 53)
        putAmharicVerses(23, 53, listOf(
            "የሰማነውን ነገር ማን አምኖአል? የእግዚአብሔርስ ክንድ ለማን ተገልጦአል?",
            "በፊቱ እንደ ቡቃያ ከደረቅም መሬት እንደ ሥር አድጎአል፤ ደም ግባትና ውበት የለውም፥ ባየነውም ጊዜ የምንወድደው መልክ አልነበረውም።",
            "የተናቀ ከሰውም የተጠላ፥ የሕማም ሰው ደዌንም የሚያውቅ ነበረ፤ ሰውም ፊቱን እንደሚሰውርበት የተናቀ ነበረ፥ እኛም አላከበርነውም።",
            "በእውነት ደዌያችንን ተቀበለ ሕመማችንንም ተሸከመ፤ እኛ ግን እንደ ተመታ በእግዚአብሔርም እንደ ተቀሠፈ እንደ ተቸገረም ቈጠርነው።",
            "እርሱ ግን ስለ መተላለፋችን ቈሰለ፥ ስለ በደላችንም ደቀቀ፤ የደኅንነታችንም ተግሣጽ በእርሱ ላይ ነበረ፥ በእርሱም ቍስል እኛ ተፈወስን።",
            "እኛ ሁላችን እንደ በጎች ተועዝተን ጠፋን፤ እያንዳንዳችን ወደ ገዛ መንገዱ አዘነበልን፤ እግዚአብሔርም የሁላችንን በደል በእርሱ ላይ አኖረው።"
        ))

        // Matthew 5 (የማቴዎስ ወንጌል 5)
        putAmharicVerses(40, 5, listOf(
            "ሕዝቡንም አይቶ ወደ ተራራ ወጣ፤ በተቀመጠም ጊዜ ደቀ መዛሙርቱ ወደ እርሱ ቀረቡ፤",
            "አፉንም ከፍቶ አስተማራቸው እንዲህም አለ፦",
            "በመንፈስ ድሆች የሆኑ ብፁዓን ናቸው፥ መንግሥተ ሰማያት የእነርሱ ናትና።",
            "የሚያዝኑ ብፁዓን ናቸው፥ መፅናናትን ያገኛሉና።",
            "የዋሆች ብፁዓን ናቸው፥ ምድርን ይወርሳሉና።",
            "ጽድቅን የሚራቡና የሚጠሙ ብፁዓን ናቸው፥ ይጠግባሉና።",
            "የሚምሩ ብፁዓን ናቸው፥ ምሕረትን ያገኛሉና።",
            "ልበ ንጹሖች ብፁዓን ናቸው፥ እግዚአብሔርን ያዩታልና።",
            "የሚያስታርቁ ብፁዓን ናቸው፥ የእግዚአብሔር ልጆች ይባላሉና።",
            "ስለ ጽድቅ የሚሰደዱ ብፁዓን ናቸው፥ መንግሥተ ሰማያት የእነርሱ ናትና።",
            "እናንተ የምድር ጨው ናችሁ፤ ጨው አልጫ ቢሆን ግን በምን ይጣፍጣል?",
            "እናንተ የዓለም ብርሃን ናችሁ፤ በተራራ ላይ ያለች ከተማ ልትሰወር አይቻላትም።",
            "መልካሙን ሥራችሁን አይተው በሰማያት ያለውን አባታችሁን እንዲያከብሩ ብርሃናችሁ እንዲሁ በሰው ፊት ይብራ።"
        ))

        // Matthew 6 (የማቴዎስ ወንጌል 6)
        putAmharicVerses(40, 6, listOf(
            "እንግዲህ እናንተስ እንዲህ ጸልዩ፦ በሰማያት የምትኖር አባታችን ሆይ፥ ስምህ ይቀደስ፤",
            "መንግሥትህ ትምጣ፤ ፈቃድህ በሰማይ እንደ ሆነች እንዲሁ በምድር ትሁን፤",
            "የዕለት እንጀራችንን ዛሬ ስጠን፤",
            "እኛም ደግሞ የበደሉንን ይቅር እንደምንል በደላችንን ይቅር በለን፤",
            "ከክፉም አድነን እንጂ ወደ ፈተና አታግባን፤ መንግሥት ያንተ ናትና ኃይልም ክብርም ለዘለዓለሙ፤ አሜን።",
            "ነገር ግን አስቀድማችሁ የእግዚአብሔርን መንግሥት ጽድቁንም ፈልጉ፥ ይህም ሁሉ ይጨመርላችኋል።",
            "ስለዚህ ለነገ አትጨነቁ፥ ነገ ለራሱ ይጨነቃልና፤ ለቀኑ ክፋቱ ይበቃዋል።"
        ))

        // John 1 (የዮሐንስ ወንጌል 1)
        putAmharicVerses(43, 1, listOf(
            "በመጀመሪያ ቃል ነበረ፥ ቃልም በእግዚአብሔር ዘንድ ነበረ፥ ቃልም እግዚአብሔር ነበረ።",
            "ይህ በመጀመሪያ በእግዚአብሔር ዘንድ ነበረ።",
            "ሁሉ በእርሱ ሆነ፥ ከሆነውም አንዳች እንኳ ያለ እርሱ አልሆነም።",
            "በእርሱ ሕይወት ነበረች፥ ሕይወትም የሰው ብርሃን ነበረች።",
            "ብርሃንም በጨለማ ይበራል፥ ጨለማም አላሸነፈውም።",
            "ከእግዚአብሔር የተላከ ስሙ ዮሐንስ የሚባል አንድ ሰው ነበረ፤",
            "እርሱ ምስክር ሆኖ ስለ ብርሃን ሊመሰክር መጣ፥ ሰዎች ሁሉ በእርሱ በኩል እንዲያምኑ።",
            "እርሱ ስለ ብርሃን ሊመሰክር መጣ እንጂ እርሱ ራሱ ብርሃን አልነበረም።",
            "ለሰው ሁሉ የሚያበራው እውነተኛው ብርሃን ወደ ዓለም ይመጣ ነበር።",
            "በዓለም ውስጥ ነበረ፥ ዓለሙም በእርሱ ሆነ፥ ዓለሙም አላወቀውም።",
            "ወደ ራሱ ወገኖች መጣ፥ የገዛ ወገኖቹም አልተቀበሉትም።",
            "ለተቀበሉት ሁሉ ግን፥ በስሙ ለሚያምኑት ለእነርሱ የእግዚአብሔር ልጆች ይሆኑ ዘንድ ሥልጣንን ሰጣቸው።",
            "እነርሱም ከእግዚአብሔር ተወለዱ እንጂ ከደም ወይም ከሥጋ ፈቃድ ወይም ከወንድ ፈቃድ አልተወለዱም።",
            "ቃልም ሥጋ ሆነ፤ ጸጋንና እውነትንም ተሞልቶ በመካከላችን አደረ፥ አንድ ልጅም ከአባቱ ዘንድ እንዳለው ክብር የሆነውን ክብሩን አየን።"
        ))

        // John 3 (የዮሐንስ ወንጌል 3)
        putAmharicVerses(43, 3, listOf(
            "ኢየሱስም መልሶ፦ እውነት እውነት እልሃለሁ፥ ሰው ከውኃና ከመንፈስ ካልተወለደ በቀር ወደ እግዚአብሔር መንግሥት ሊገባ አይችልም አለው።",
            "ከሥጋ የተወለደ ሥጋ ነው፥ ከመንፈስም የተወለደ መንፈስ ነው።",
            "ዳግመኛ ልትወለዱ ያስፈልጋችኋል ስላልሁህ አትደነቅ።",
            "ነፋስ ወደሚወደው ይነፍሳል፥ ድምፁንም ትሰማለህ፥ ነገር ግን ከወዴት እንደ መጣ ወዴትም እንደሚሄድ አታውቅም፤ ከመንፈስ የተወለደ ሁሉ እንዲሁ ነው።",
            "ሙሴም በምድረ በዳ እባብን እንደ ሰቀለ እንዲሁ በእርሱ የሚያምን ሁሉ የዘላለም ሕይወት እንዲኖረው እንጂ እንዳይጠፋ የሰው ልጅ ይሰቀል ይገባዋል።",
            "በእርሱ የሚያምን ሁሉ የዘላለም ሕይወት እንዲኖረው እንጂ እንዳይጠፋ እግዚአብሔር አንድያ ልጁን እስኪሰጥ ድረስ ዓለሙን እንዲሁ ወዶአልና።",
            "ዓለም በልጁ እንዲድን እንጂ በዓለም እንዲፈርድ እግዚአብሔር ልጁን ወደ ዓለም አልላከውምና።",
            "በእርሱ በሚያምን አይፈረድበትም፤ በማያምን ግን በአንዱ በእግዚአብሔር ልጅ ስም ስላላመነ አሁን ተፈርዶበታል።"
        ))

        // John 14 (የዮሐንስ ወንጌል 14)
        putAmharicVerses(43, 14, listOf(
            "ልባችሁ አይታወክ፤ በእግዚአብሔር እመኑ፥ በእኔም ደግሞ እመኑ።",
            "በአባቴ ቤት ብዙ መኖሪያ አለ፤ እንዲህስ ባይሆን ባልኋችሁ ነበር፤ ስፍራ ላዘጋጅላችሁ እሄዳለሁና፤",
            "ሄጄም ስፍራ ባዘጋጅላችሁ፥ እኔ ባለሁበት እናንተ ደግሞ እንድትሆኑ ሁለተኛ እመጣለሁ ወደ እኔም እወስዳችኋለሁ።",
            "ወደምሄድበትም ታውቃላችሁ፥ መንገዱንም ታውቃላችሁ።",
            "ቶማስም፦ ጌታ ሆይ፥ ወዴት እንደምትሄድ አናውቅም፤ እንዴትስ መንገዱን እናውቃለን? አለው።",
            "ኢየሱስም፦ እኔ መንገድና እውነት ሕይወትም ነኝ፤ በእኔ በቀር ወደ አብ የሚመጣ የለም።",
            "ሰላምን እተውላችኋለሁ፥ ሰላሜን እሰጣችኋለሁ፤ እኔ የምሰጣችሁ ዓለም እንደሚሰጥ አይደለም። ልባችሁ አይታወክ አይፍራምም።"
        ))

        // Romans 8 (ወደ ሮሜ ሰዎች 8)
        putAmharicVerses(45, 8, listOf(
            "እንግዲህ በክርስቶስ ኢየሱስ ላሉት አሁን ምንም ኩነኔ የለባቸውም።",
            "በክርስቶስ ኢየሱስ ያለው የሕይወት መንፈስ ሕግ ከኃጢአትና ከሞት ሕግ አርነት አውጥቶኛልና።",
            "የእግዚአብሔር መንፈስ የሚመራቸው ሁሉ እነዚህ የእግዚአብሔር ልጆች ናቸውና።",
            "አባ አባት ብለን የምንጮኽበትን የልጅነት መንፈስ ተቀበላችሁ እንጂ እንደገና ለፍርሃት የባርነትን መንፈስ አልተቀበላችሁምና።",
            "እግዚአብሔርንም ለሚወዱት እንደ አሳቡም ለተጠሩት ነገር ሁሉ ለበጎ እንዲደረግ እናውቃለን።",
            "እንግዲህ ስለዚህ ነገር ምን እንላለን? እግዚአብሔር ከእኛ ጋር ከሆነ ማን ይቃወመናል?",
            "ለገዛ ልጁ ያልራራለት ነገር ግን ስለ ሁላችን አሳልፎ የሰጠው ያው ከእርሱ ጋር ደግሞ ሁሉን ነገር እንዲያው እንዴት አይሰጠንም?",
            "ከክርስቶስ ፍቅር ማን ይለየናል? መከራ፥ ወይስ ጭንቀት፥ ወይስ ስደት፥ ወይስ ራብ፥ ወይስ ራቁትነት፥ ወይስ አደጋ፥ ወይስ ሰይፍ ነውን?",
            "ነገር ግን በዚህ ሁሉ በወደደን በእርሱ ከአሸናፊዎች እንበልጣለን።",
            "ሞት ቢሆን፥ ሕይወትም ቢሆን፥ መላእክትም ቢሆኑ፥ ግዛትም ቢሆን፥ ያለውም ቢሆን፥ የሚመጣውም ቢሆን፥ ኃይላትም ቢሆኑ፥ ከፍታም ቢሆን፥ ዝቅታም ቢሆን፥ ልዩ ፍጥረትም ቢሆን በክርስቶስ ኢየሱስ በጌታችን ካለው ከእግዚአብሔር ፍቅር ሊለየን እንዳይችል ተረድቼአለሁ።"
        ))

        // 1 Corinthians 13 (1 ወደ ቆሮንቶስ ሰዎች 13)
        putAmharicVerses(46, 13, listOf(
            "በሰዎችና በመላእክት ልሳን ብናገር ፍቅር ግን ከሌለኝ እንደሚጮኽ ናስ ወይም እንደሚንሽዋሽዋ ጸናጽል ሆኛለሁ።",
            "ትንቢትም ቢኖረኝ ምሥጢርንም ሁሉና እውቀትን ሁሉ ባውቅ፥ ተራሮችንም እስካፈልስ ድረስ እምነት ሁሉ ቢኖረኝ ፍቅር ግን ከሌለኝ ከንቱ ነኝ።",
            "ድሆችንም ልመግብ ያለኝን ሁሉ ባካፍል፥ ሥጋዬንም ለእሳት መቃጠል አሳልፌ ብሰጥ ፍቅር ግን ከሌለኝ ምንም አይጠቅመኝም።",
            "ፍቅር ይታገሣል፥ ቸርነትንም ያደርጋል፤ ፍቅር አይቀናም፤ ፍቅር አይመካም፥ አይታበይም፤",
            "የማይገባውን አያደርግም፥ የራሱንም አይፈልግም፥ አይበሳጭም፥ በደልን አይቈጥርም፤",
            "ከእውነት ጋር ደስ ይለዋል እንጂ ስለ ዓመፃ ደስ አይለውም፤",
            "ሁሉን ይታገሣል፥ ሁሉን ያምናል፥ ሁሉን ተስፋ ያደርጋል፥ በሁሉ ይጸናል።",
            "ፍቅር ለዘወትር አይወድቅም፤ ትንቢት ቢሆን ግን ይሻራል፤ ልሳኖች ቢሆኑ ይቀራሉ፤ እውቀትም ቢሆን ይሻራል።",
            "አሁን ግን እምነት፥ ተስፋ፥ ፍቅር እነዚህ ሦስቱ ጸንተው ይኖራሉ፤ ከእነዚህም የሚበልጠው ፍቅር ነው።"
        ))

        // Revelation 21 (የዮሐንስ ራእይ 21)
        putAmharicVerses(66, 21, listOf(
            "አዲስ ሰማይንና አዲሲትንም ምድር አየሁ፥ ፊተኛው ሰማይና ፊተኛይቱ ምድር አልፈዋልና፥ ባሕርም ወደ ፊት የለም።",
            "ቅድስቲቱም ከተማ አዲሲቱ ኢየሩሳሌም ለባልዋ እንደ ተሸለመች ሙሽራ ተዘጋጅታ ከሰማይ ከእግዚአብሔር ዘንድ ስትወርድ አየሁ።",
            "ታላቅም ድምፅ ከሰማይ፦ እነሆ፥ የእግዚአብሔር ድንኳን በሰዎች መካከል ነው ከእነርሱም ጋር ያድራል፥ እነርሱም ሕዝቡ ይሆናሉ እግዚአብሔርም ራሱ ከእነርሱ ጋር ሆኖ አምላካቸው ይሆናል፤",
            "እንባዎችንም ሁሉ ከዓይኖቻቸው ያብሳል፥ ሞትም ከእንግዲህ ወዲህ አይሆንም፥ ኀዘንም ቢሆን ወይም ጩኸት ወይም ሥቃይ ከእንግዲህ ወዲህ አይሆንም፥ የቀደመው ሥርዓት አልፎአልና ብሎ ሲናገር ሰማሁ።",
            "በዙፋንም የተቀመጠው፦ እነሆ፥ ሁሉን አዲስ አደርጋለሁ አለ። ለእኔም፦ እነዚህ ቃሎች የታመኑና እውነተኛዎች ናቸውና ጻፍ አለኝ።",
            "አለኝም፦ ተፈጽሞአል። አልፋና ዖሜጋ፥ መጀመሪያውና መጨረሻው እኔ ነኝ። ለተጠማ ከሕይወት ውኃ ምንጭ እንዲያው እኔ እሰጣለሁ።",
            "ድል የሚነሣ ይህን ይወርሳል አምላክም እሆነዋለሁ እርሱም ልጅ ይሆነኛል።"
        ))
    }

    fun loadAmharicAssets(context: Context) {
        if (isAmharicAssetsLoaded) return
        try {
            val assetFiles = context.assets.list("bible")?.filter { it.startsWith("amharic_") && it.endsWith(".json") }
                ?: listOf(
                    "amharic_genesis.json",
                    "amharic_exodus_part1.json",
                    "amharic_exodus_part2.json",
                    "amharic_exodus_part3.json",
                    "amharic_exodus_part4.json",
                    "amharic_leviticus_part1.json",
                    "amharic_leviticus_part2.json",
                    "amharic_numbers_part1.json",
                    "amharic_numbers_part2.json",
                    "amharic_numbers_part3.json",
                    "amharic_deuteronomy_part1.json",
                    "amharic_deuteronomy_part2.json",
                    "amharic_deuteronomy_part3.json",
                    "amharic_joshua_part1.json",
                    "amharic_joshua_part2.json",
                    "amharic_judges_part1.json",
                    "amharic_judges_part2.json",
                    "amharic_ruth.json",
                    "amharic_1samuel_part1.json",
                    "amharic_1samuel_part2.json",
                    "amharic_2samuel_part1.json",
                    "amharic_2samuel_part2.json",
                    "amharic_1kings_part1.json",
                    "amharic_1kings_part2.json",
                    "amharic_2kings_part1.json",
                    "amharic_2kings_part2.json",
                    "amharic_2kings_part3.json",
                    "amharic_1chronicles_part1.json",
                    "amharic_1chronicles_part2.json",
                    "amharic_2chronicles_part1.json",
                    "amharic_2chronicles_part2.json",
                    "amharic_2chronicles_part3.json",
                    "amharic_ezra.json",
                    "amharic_nehemiah.json",
                    "amharic_esther.json",
                    "amharic_job_part1.json",
                    "amharic_job_part2.json",
                    "amharic_job_part3.json",
                    "amharic_psalms_part1.json",
                    "amharic_psalms_part2.json",
                    "amharic_psalms_part3.json",
                    "amharic_psalms_part4.json",
                    "amharic_proverbs.json",
                    "amharic_ecclesiastes.json",
                    "amharic_songofsolomon.json",
                    "amharic_isaiah_part1.json",
                    "amharic_isaiah_part2.json",
                    "amharic_jeremiah_part1.json",
                    "amharic_jeremiah_part2.json",
                    "amharic_lamentations.json",
                    "amharic_ezekiel_part1.json",
                    "amharic_ezekiel_part2.json",
                    "amharic_daniel.json",
                    "amharic_hosea.json",
                    "amharic_joel.json",
                    "amharic_amos.json",
                    "amharic_obadiah.json",
                    "amharic_jonah.json",
                    "amharic_micah.json",
                    "amharic_nahum.json",
                    "amharic_habakkuk.json",
                    "amharic_zephaniah.json",
                    "amharic_haggai.json",
                    "amharic_zechariah.json",
                    "amharic_malachi.json"
                )

            var totalChaptersLoaded = 0
            for (filename in assetFiles) {
                try {
                    val jsonString = context.assets.open("bible/$filename").bufferedReader().use { it.readText() }
                    val root = JSONObject(jsonString)
                    val title = root.optString("title", "")
                    val explicitBookId = root.optInt("bookId", -1)

                    val book = (if (explicitBookId > 0) BibleDataProvider.getBookById(explicitBookId) else null)
                        ?: BibleDataProvider.getBookByName(title)
                        ?: if (filename.contains("genesis")) BibleDataProvider.getBookById(1)
                        else if (filename.contains("exodus")) BibleDataProvider.getBookById(2)
                        else if (filename.contains("leviticus")) BibleDataProvider.getBookById(3)
                        else if (filename.contains("numbers")) BibleDataProvider.getBookById(4)
                        else if (filename.contains("deuteronomy")) BibleDataProvider.getBookById(5)
                        else if (filename.contains("joshua")) BibleDataProvider.getBookById(6)
                        else if (filename.contains("judges")) BibleDataProvider.getBookById(7)
                        else if (filename.contains("ruth")) BibleDataProvider.getBookById(8)
                        else if (filename.contains("1samuel")) BibleDataProvider.getBookById(9)
                        else if (filename.contains("2samuel")) BibleDataProvider.getBookById(10)
                        else if (filename.contains("1kings")) BibleDataProvider.getBookById(11)
                        else if (filename.contains("2kings")) BibleDataProvider.getBookById(12)
                        else if (filename.contains("1chronicles")) BibleDataProvider.getBookById(13)
                        else if (filename.contains("2chronicles")) BibleDataProvider.getBookById(14)
                        else if (filename.contains("ezra")) BibleDataProvider.getBookById(15)
                        else if (filename.contains("nehemiah")) BibleDataProvider.getBookById(16)
                        else if (filename.contains("esther")) BibleDataProvider.getBookById(17)
                        else if (filename.contains("job")) BibleDataProvider.getBookById(18)
                        else if (filename.contains("psalms")) BibleDataProvider.getBookById(19)
                        else if (filename.contains("proverbs")) BibleDataProvider.getBookById(20)
                        else if (filename.contains("ecclesiastes")) BibleDataProvider.getBookById(21)
                        else if (filename.contains("songofsolomon")) BibleDataProvider.getBookById(22)
                        else if (filename.contains("isaiah")) BibleDataProvider.getBookById(23)
                        else if (filename.contains("jeremiah")) BibleDataProvider.getBookById(24)
                        else if (filename.contains("lamentations")) BibleDataProvider.getBookById(25)
                        else if (filename.contains("ezekiel")) BibleDataProvider.getBookById(26)
                        else if (filename.contains("daniel")) BibleDataProvider.getBookById(27)
                        else if (filename.contains("hosea")) BibleDataProvider.getBookById(28)
                        else if (filename.contains("joel")) BibleDataProvider.getBookById(29)
                        else if (filename.contains("amos")) BibleDataProvider.getBookById(30)
                        else if (filename.contains("obadiah")) BibleDataProvider.getBookById(31)
                        else if (filename.contains("jonah")) BibleDataProvider.getBookById(32)
                        else if (filename.contains("micah")) BibleDataProvider.getBookById(33)
                        else if (filename.contains("nahum")) BibleDataProvider.getBookById(34)
                        else if (filename.contains("habakkuk")) BibleDataProvider.getBookById(35)
                        else if (filename.contains("zephaniah")) BibleDataProvider.getBookById(36)
                        else if (filename.contains("haggai")) BibleDataProvider.getBookById(37)
                        else if (filename.contains("zechariah")) BibleDataProvider.getBookById(38)
                        else if (filename.contains("malachi")) BibleDataProvider.getBookById(39)
                        else null

                    if (book == null) {
                        Log.w("BibleScriptureStore", "Could not identify book for asset file: $filename (title: $title)")
                        continue
                    }

                    val chaptersArray = root.optJSONArray("chapters") ?: continue
                    for (i in 0 until chaptersArray.length()) {
                        val chapObj = chaptersArray.getJSONObject(i)
                        val chapNum = chapObj.optString("chapter", "${i + 1}").toIntOrNull() ?: (i + 1)
                        val versesArray = chapObj.optJSONArray("verses") ?: continue
                        val verseList = mutableListOf<BibleVerse>()

                        for (v in 0 until versesArray.length()) {
                            val text = versesArray.getString(v)
                            verseList.add(
                                BibleVerse(
                                    bookId = book.id,
                                    bookName = book.amharicName,
                                    chapter = chapNum,
                                    verse = v + 1,
                                    text = text,
                                    translation = BibleTranslation.AMHARIC
                                )
                            )
                        }
                        amharicKeyVersesMap["${book.id}_$chapNum"] = verseList
                        totalChaptersLoaded++
                    }
                } catch (fe: Exception) {
                    Log.e("BibleScriptureStore", "Error loading asset file $filename: ${fe.message}", fe)
                }
            }
            isAmharicAssetsLoaded = true
            Log.d("BibleScriptureStore", "Successfully loaded $totalChaptersLoaded Amharic chapters across ${assetFiles.size} asset files.")
        } catch (e: Exception) {
            Log.e("BibleScriptureStore", "Could not load Amharic assets: ${e.message}", e)
        }
    }

    private fun putVerses(bookName: String, chapter: Int, texts: List<String>) {
        val book = BibleDataProvider.getBookByName(bookName) ?: return
        val list = texts.mapIndexed { index, text ->
            BibleVerse(
                bookId = book.id,
                bookName = book.name,
                chapter = chapter,
                verse = index + 1,
                text = text,
                translation = BibleTranslation.KJV
            )
        }
        keyVersesMap["${book.id}_$chapter"] = list
    }

    private fun putAmharicVerses(bookId: Int, chapter: Int, texts: List<String>) {
        val book = BibleDataProvider.getBookById(bookId) ?: return
        val list = texts.mapIndexed { index, text ->
            BibleVerse(
                bookId = book.id,
                bookName = book.amharicName,
                chapter = chapter,
                verse = index + 1,
                text = text,
                translation = BibleTranslation.AMHARIC
            )
        }
        amharicKeyVersesMap["${book.id}_$chapter"] = list
    }

    fun getVersesForChapter(
        bookId: Int,
        chapter: Int,
        translation: BibleTranslation = BibleTranslation.AMHARIC
    ): List<BibleVerse> {
        val key = "${bookId}_$chapter"
        val book = BibleDataProvider.getBookById(bookId) ?: return emptyList()

        if (translation == BibleTranslation.AMHARIC) {
            val amhVerses = amharicKeyVersesMap[key]
            if (amhVerses != null && amhVerses.isNotEmpty()) {
                return amhVerses
            }
            return generateAmharicChapterVerses(book, chapter)
        } else {
            val engVerses = keyVersesMap[key]
            if (engVerses != null && engVerses.isNotEmpty()) {
                return engVerses
            }
            return generateChapterVerses(book, chapter)
        }
    }

    private fun generateAmharicChapterVerses(book: com.example.data.model.BibleBook, chapter: Int): List<BibleVerse> {
        val amharicSampleVerses = when (book.testament) {
            com.example.data.model.Testament.OLD -> listOf(
                "እግዚአብሔር ነግሦአል፤ ምድር ሐሤት ታድርግ፥ ብዙ ደሴቶችም ደስ ይበላቸው።",
                "እግዚአብሔርን አመስግኑ፥ ስሙንም ጥሩ፤ ለአሕዛብም ሥራውን አውሩ።",
                "ለዘላለም ቃል ኪዳኑን፥ ለሺህ ትውልድ ያዘዘውን ቃል አሰበ።",
                "አሕዛብ ሁላችሁ፥ እግዚአብሔርን አመስግኑ፤ ወገኖችም ሁላችሁ፥ አመስግኑት።",
                "በእግዚአብሔር ታመን መልካምንም አድርግ፤ በምድርም ተቀመጥ፥ እውነተኛውንም ምግብ ተመገብ።",
                "መንገድህን ለእግዚአብሔር አደራ ስጥ፥ በእርሱም ታመን፥ እርሱም ያደርግልሃል።",
                "ለእግዚአብሔር ተገዛ፥ በእርሱም ታገሥ፤ በመንገዱ በሚከናወንለት ክፉ አሳብንም በሚያደርግ ሰው አትቅና።",
                "የጻድቅ ሰው አካሄድ በእግዚአብሔር ይጸናል፥ መንገዱንም ይወድድለታል።",
                "ቢወድቅም አይጣልም፥ እግዚአብሔር እጁን ይዞታልና።",
                "ጐበዝ ነበርሁ አረጀሁም፤ ጻድቅ ሲጣል ዘሩም እህል ሲለምን አላየሁም።",
                "እግዚአብሔር ፍርድን ይወዳልና፥ ቅዱሳኑንም አይጥላቸውምና፤ ለዘላለምም ይጠበቃሉ።",
                "የጻድቅ አፍ ጥበብን ይናገራል፥ አንደበቱም ፍርድን ያወራል።",
                "የአምላኩ ሕግ በልቡ ውስጥ ነው፥ በእርምጃውም አይሰናከልም።",
                "እግዚአብሔርን ደጅ ጸና፥ መንገዱንም ጠብቅ፥ ምድርንም ትወርስ ዘንድ ከፍ ከፍ ያደርግሃል።",
                "ፍጹሙን ሰው ተመልከት ቅኑንም እይ፥ የሰላም ሰው ፍጻሜ አለውና።"
            )
            com.example.data.model.Testament.NEW -> listOf(
                "ከአባታችን ከእግዚአብሔር ከጌታም ከኢየሱስ ክርስቶስ ጸጋና ሰላም ለእናንተ ይሁን።",
                "ሁልጊዜ በጸሎቴ ሁሉ ስለ እናንተ በደስታ እየጸለይሁ፥ ባሰብኋችሁ ጊዜ ሁሉ አምላኬን አመሰግናለሁ።",
                "በእናንተ መልካምን ሥራ የጀመረው እስከ ኢየሱስ ክርስቶስ ቀን ድረስ እንዲፈጽመው ይህን ተረድቼአለሁና።",
                "በኢየሱስ ክርስቶስ ፍቅር ሁላችሁን እንዴት እንደምናፍቃችሁ እግዚአብሔር ምስክሬ ነውና።",
                "ፍቅራችሁ በእውቀትና በማስተዋል ሁሉ ከፊት ይልቅ እያደገ እንዲበዛ እጸልያለሁ፤",
                "የሚሻለውን እንድትመረምሩ፤ ለክርስቶስ ቀን ቅኖችና ያለ ነውር እንድትሆኑ፤",
                "ለእግዚአብሔር ክብርና ምስጋና በኢየሱስ ክርስቶስ የሚገኝ የጽድቅ ፍሬ የተመላባችሁ እንድትሆኑ።",
                "ለእኔ ሕይወት ክርስቶስ ነውና፥ ሞትም ጥቅም ነው።",
                "ብቻ ለክርስቶስ ወንጌል እንደሚገባ ኑሩ፤ ብመጣና ባያችሁ ወይም ባልመጣ፥ በአንድ ልብ ስለ ወንጌል እምነት እየተጋደላችሁ በአንድ መንፈስ እንደምትቆሙ እሰማ ዘንድ።",
                "በተቃዋሚዎች በአንዳች እንኳ አትደንግጡ፤ ይህም ለእነርሱ የጥፋት ምልክት ነው፥ ለእናንተ ግን የመዳን ምልክት ነው።",
                "ይህም ከእግዚአብሔር ነው፤ ስለ ክርስቶስ ልታምኑበት ብቻ ሳይሆን ስለ እርሱ መከራ ደግሞ ልትቀበሉ ተሰጥቶአችኋልና።"
            )
        }

        return amharicSampleVerses.mapIndexed { index, text ->
            BibleVerse(
                bookId = book.id,
                bookName = book.amharicName,
                chapter = chapter,
                verse = index + 1,
                text = text,
                translation = BibleTranslation.AMHARIC
            )
        }
    }

    private fun generateChapterVerses(book: com.example.data.model.BibleBook, chapter: Int): List<BibleVerse> {
        val sampleVerses = when (book.testament) {
            com.example.data.model.Testament.OLD -> listOf(
                "The LORD reigneth, let the earth rejoice; let the multitude of isles be glad thereof.",
                "Give thanks unto the LORD, call upon his name, make known his deeds among the people.",
                "He hath remembered his covenant for ever, the word which he commanded to a thousand generations.",
                "O praise the LORD, all ye nations: praise him, all ye people. For his merciful kindness is great toward us.",
                "Trust in the LORD, and do good; so shalt thou dwell in the land, and verily thou shalt be fed.",
                "Commit thy way unto the LORD; trust also in him; and he shall bring it to pass.",
                "Rest in the LORD, and wait patiently for him: fret not thyself because of him who prospereth in his way.",
                "The steps of a good man are ordered by the LORD: and he delighteth in his way.",
                "Though he fall, he shall not be utterly cast down: for the LORD upholdeth him with his hand.",
                "I have been young, and now am old; yet have I not seen the righteous forsaken, nor his seed begging bread.",
                "For the LORD loveth judgment, and forsaketh not his saints; they are preserved for ever.",
                "The mouth of the righteous speaketh wisdom, and his tongue talketh of judgment.",
                "The law of his God is in his heart; none of his steps shall slide.",
                "Wait on the LORD, and keep his way, and he shall exalt thee to inherit the land.",
                "Mark the perfect man, and behold the upright: for the end of that man is peace."
            )
            com.example.data.model.Testament.NEW -> listOf(
                "Grace be unto you, and peace, from God our Father, and from the Lord Jesus Christ.",
                "I thank my God upon every remembrance of you, always in every prayer of mine for you all making request with joy.",
                "Being confident of this very thing, that he which hath begun a good work in you will perform it until the day of Jesus Christ.",
                "For God is my record, how greatly I long after you all in the bowels of Jesus Christ.",
                "And this I pray, that your love may abound yet more and more in knowledge and in all judgment;",
                "That ye may approve things that are excellent; that ye may be sincere and without offence till the day of Christ;",
                "Being filled with the fruits of righteousness, which are by Jesus Christ, unto the glory and praise of God.",
                "For to me to live is Christ, and to die is gain.",
                "Only let your conversation be as it becometh the gospel of Christ: that whether I come and see you, or else be absent, I may hear of your affairs.",
                "Stand fast in one spirit, with one mind striving together for the faith of the gospel;",
                "And in nothing terrified by your adversaries: which is to them an evident token of perdition, but to you of salvation, and that of God.",
                "For unto you it is given in the behalf of Christ, not only to believe on him, but also to suffer for his sake."
            )
        }

        return sampleVerses.mapIndexed { index, text ->
            BibleVerse(
                bookId = book.id,
                bookName = book.name,
                chapter = chapter,
                verse = index + 1,
                text = text
            )
        }
    }

    // Daily Verses with reflections and prayers
    val dailyVerses: List<DailyVerse> = listOf(
        DailyVerse(
            id = 1,
            bookName = "Philippians",
            chapter = 4,
            verse = 6,
            text = "Be careful for nothing; but in every thing by prayer and supplication with thanksgiving let your requests be made known unto God.",
            theme = "Peace in All Circumstances",
            reflection = "Worry accomplishes nothing, but turning every anxious thought into a prayer releases God's supernatural peace that transcends understanding.",
            prayer = "Heavenly Father, today I surrender my anxieties and uncertainties into Your hands. Thank You for Your steadfast peace guarding my mind and heart. Amen."
        ),
        DailyVerse(
            id = 2,
            bookName = "Proverbs",
            chapter = 3,
            verse = 5,
            text = "Trust in the LORD with all thine heart; and lean not unto thine own understanding.",
            theme = "Complete Trust",
            reflection = "Our human perspective is limited, but God sees the entire path before us. Wholehearted trust means choosing His direction over our own assumptions.",
            prayer = "Lord God, teach me to lean completely on Your wisdom. Direct my steps today and give me clarity to walk according to Your perfect will. Amen."
        ),
        DailyVerse(
            id = 3,
            bookName = "Isaiah",
            chapter = 40,
            verse = 31,
            text = "But they that wait upon the LORD shall renew their strength; they shall mount up with wings as eagles; they shall run, and not be weary; and they shall walk, and not faint.",
            theme = "Renewed Strength",
            reflection = "Waiting on the Lord is an active posture of hope. When our energy fails, His supernatural power lifts us up and sustains our journey.",
            prayer = "Dear Lord, when I feel weary and overwhelmed, fill me with Your Holy Spirit's renewal. Give me strength to soar above life's storms. Amen."
        ),
        DailyVerse(
            id = 4,
            bookName = "Psalms",
            chapter = 23,
            verse = 1,
            text = "The LORD is my shepherd; I shall not want.",
            theme = "Divine Provision",
            reflection = "Because the Almighty Shepherd leads and watches over us, we lack nothing essential. He leads us to quiet waters and restores our inner souls.",
            prayer = "Good Shepherd, thank You for Your loving care, guidance, and continuous provision. I rest securely under Your protection today. Amen."
        ),
        DailyVerse(
            id = 5,
            bookName = "Romans",
            chapter = 8,
            verse = 28,
            text = "And we know that all things work together for good to them that love God, to them who are the called according to his purpose.",
            theme = "God's Sovereign Purpose",
            reflection = "Even in unforeseen trials or delays, God weaves every thread of our lives into a tapestry of eternal blessing and spiritual maturity.",
            prayer = "Father, thank You that my future is in Your sovereign hands. Turn every challenge today into an opportunity for Your glory. Amen."
        ),
        DailyVerse(
            id = 6,
            bookName = "Joshua",
            chapter = 1,
            verse = 9,
            text = "Have not I commanded thee? Be strong and of a good courage; be not afraid, neither be thou dismayed: for the LORD thy God is with thee whithersoever thou goest.",
            theme = "Courage & Boldness",
            reflection = "True courage is not the absence of fear, but the assurance that the Lord God is walking right beside us into every unknown territory.",
            prayer = "Lord Almighty, banish all hesitation and fear from my heart. Fill me with holy boldness to fulfill what You have called me to do today. Amen."
        ),
        DailyVerse(
            id = 7,
            bookName = "John",
            chapter = 14,
            verse = 27,
            text = "Peace I leave with you, my peace I give unto you: not as the world giveth, give I unto you. Let not your heart be troubled, neither let it be afraid.",
            theme = "Christ's Gift of Peace",
            reflection = "Worldly peace depends on comfortable surroundings; Christ's peace anchors our spirit regardless of the storm raging outside.",
            prayer = "Jesus, Prince of Peace, calm every storm within me. May Your quiet assurance reign supreme in my thoughts and conversations today. Amen."
        )
    )

    // Topical Collections for instant spiritual encouragement
    val topicalCollections: List<TopicalVerse> = listOf(
        TopicalVerse(
            topic = "Peace & Comfort",
            topicDescription = "God's calming presence when life feels overwhelming and stormy",
            iconName = "spa",
            verses = listOf(
                BibleVerse(19, "Psalms", 23, 4, "Yea, though I walk through the valley of the shadow of death, I will fear no evil: for thou art with me; thy rod and thy staff they comfort me."),
                BibleVerse(50, "Philippians", 4, 7, "And the peace of God, which passeth all understanding, shall keep your hearts and minds through Christ Jesus."),
                BibleVerse(43, "John", 14, 27, "Peace I leave with you, my peace I give unto you: not as the world giveth, give I unto you. Let not your heart be troubled, neither let it be afraid."),
                BibleVerse(19, "Psalms", 91, 1, "He that dwelleth in the secret place of the most High shall abide under the shadow of the Almighty.")
            )
        ),
        TopicalVerse(
            topic = "Faith & Trust",
            topicDescription = "Anchoring your heart in God's promises and timeless faithfulness",
            iconName = "shield",
            verses = listOf(
                BibleVerse(58, "Hebrews", 11, 1, "Now faith is the substance of things hoped for, the evidence of things not seen."),
                BibleVerse(20, "Proverbs", 3, 5, "Trust in the LORD with all thine heart; and lean not unto thine own understanding."),
                BibleVerse(46, "2 Corinthians", 5, 7, "For we walk by faith, not by sight:"),
                BibleVerse(40, "Matthew", 17, 20, "If ye have faith as a grain of mustard seed, ye shall say unto this mountain, Remove hence to yonder place; and it shall remove; and nothing shall be impossible unto you.")
            )
        ),
        TopicalVerse(
            topic = "God's Unfailing Love",
            topicDescription = "The depth, width, and eternal security of God's love for you",
            iconName = "favorite",
            verses = listOf(
                BibleVerse(43, "John", 3, 16, "For God so loved the world, that he gave his only begotten Son, that whosoever believeth in him should not perish, but have everlasting life."),
                BibleVerse(45, "Romans", 8, 38, "For I am persuaded, that neither death, nor life, nor angels, nor principalities, nor powers... shall be able to separate us from the love of God, which is in Christ Jesus our Lord."),
                BibleVerse(62, "1 John", 4, 19, "We love him, because he first loved us."),
                BibleVerse(24, "Jeremiah", 31, 3, "The LORD hath appeared of old unto me, saying, Yea, I have loved thee with an everlasting love: therefore with lovingkindness have I drawn thee.")
            )
        ),
        TopicalVerse(
            topic = "Strength in Hardship",
            topicDescription = "Supernatural energy and perseverance when facing trials and exhaustion",
            iconName = "bolt",
            verses = listOf(
                BibleVerse(50, "Philippians", 4, 13, "I can do all things through Christ which strengtheneth me."),
                BibleVerse(23, "Isaiah", 40, 29, "He giveth power to the faint; and to them that have no might he increaseth strength."),
                BibleVerse(19, "Psalms", 46, 1, "God is our refuge and strength, a very present help in trouble."),
                BibleVerse(47, "2 Corinthians", 12, 9, "And he said unto me, My grace is sufficient for thee: for my strength is made perfect in weakness.")
            )
        ),
        TopicalVerse(
            topic = "Wisdom & Guidance",
            topicDescription = "Seeking God's discernment and direction for daily decisions",
            iconName = "lightbulb",
            verses = listOf(
                BibleVerse(59, "James", 1, 5, "If any of you lack wisdom, let him ask of God, that giveth to all men liberally, and upbraideth not; and it shall be given him."),
                BibleVerse(19, "Psalms", 119, 105, "Thy word is a lamp unto my feet, and a light unto my path."),
                BibleVerse(20, "Proverbs", 4, 7, "Wisdom is the principal thing; therefore get wisdom: and with all thy getting get understanding."),
                BibleVerse(20, "Proverbs", 16, 3, "Commit thy works unto the LORD, and thy thoughts shall be established.")
            )
        ),
        TopicalVerse(
            topic = "Gratitude & Joy",
            topicDescription = "Rejoicing in the Lord and offering joyful praise in all seasons",
            iconName = "celebration",
            verses = listOf(
                BibleVerse(52, "1 Thessalonians", 5, 16, "Rejoice evermore. Pray without ceasing. In every thing give thanks: for this is the will of God in Christ Jesus concerning you."),
                BibleVerse(19, "Psalms", 100, 2, "Serve the LORD with gladness: come before his presence with singing."),
                BibleVerse(19, "Psalms", 118, 24, "This is the day which the LORD hath made; we will rejoice and be glad in it."),
                BibleVerse(16, "Nehemiah", 8, 10, "For the joy of the LORD is your strength.")
            )
        )
    )

    // Reading Plans
    val readingPlans: List<ReadingPlan> = listOf(
        ReadingPlan(
            id = "plan_whole_bible_365",
            title = "365-Day Whole Bible Journey",
            subtitle = "From Genesis to Revelation in One Year",
            description = "Experience the complete grand narrative of Scripture through daily readings balancing Old Testament, New Testament, Psalms, and Wisdom.",
            durationDays = 365,
            category = "Comprehensive",
            days = (1..365).map { day ->
                val bookIndex = ((day - 1) % 66)
                val book = BibleDataProvider.books[bookIndex]
                val chapter = ((day - 1) / 66) + 1
                val clampedChapter = chapter.coerceIn(1, book.chaptersCount)
                ReadingPlanDay(
                    dayNumber = day,
                    title = "Day $day: The Eternal Word",
                    passageReference = "${book.name} $clampedChapter",
                    bookId = book.id,
                    chapter = clampedChapter,
                    devotionalNote = "Today we dive into ${book.name} chapter $clampedChapter. Meditate on God's character and how this revelation applies to your walk today."
                )
            }
        ),
        ReadingPlan(
            id = "plan_new_testament_90",
            title = "90-Day New Testament Challenge",
            subtitle = "Read Matthew through Revelation",
            description = "Walk closely with Jesus and the early Apostles across all 27 New Testament books in 90 inspiring daily readings.",
            durationDays = 90,
            category = "New Testament",
            days = (1..90).map { day ->
                val ntBooks = BibleDataProvider.books.filter { it.testament == com.example.data.model.Testament.NEW }
                val book = ntBooks[(day - 1) % ntBooks.size]
                val chapter = ((day - 1) / ntBooks.size) + 1
                val clampedChapter = chapter.coerceIn(1, book.chaptersCount)
                ReadingPlanDay(
                    dayNumber = day,
                    title = "Day $day: Christ & Grace",
                    passageReference = "${book.name} $clampedChapter",
                    bookId = book.id,
                    chapter = clampedChapter,
                    devotionalNote = "Reflect on how ${book.name} speaks of the finished work of Jesus Christ and empowers your daily Christian walk."
                )
            }
        ),
        ReadingPlan(
            id = "plan_psalms_proverbs_30",
            title = "30-Day Psalms & Proverbs Peace",
            subtitle = "Daily Wisdom & Heartfelt Worship",
            description = "Calm your soul each morning and evening with the poetic beauty of David's Psalms and the practical discernment of Solomon's Proverbs.",
            durationDays = 30,
            category = "Wisdom & Peace",
            days = (1..30).map { day ->
                val psalmChapter = day * 5
                val clampedPsalm = psalmChapter.coerceIn(1, 150)
                val proverbsChapter = day.coerceIn(1, 31)
                ReadingPlanDay(
                    dayNumber = day,
                    title = "Day $day: Songs & Wisdom",
                    passageReference = "Psalms $clampedPsalm & Proverbs $proverbsChapter",
                    bookId = 19, // Psalms
                    chapter = clampedPsalm,
                    devotionalNote = "Let the worship in Psalm $clampedPsalm refresh your spirit and the counsel of Proverbs $proverbsChapter guide your thoughts."
                )
            }
        ),
        ReadingPlan(
            id = "plan_words_of_jesus_21",
            title = "21-Day Life & Miracles of Jesus",
            subtitle = "A Gospel Deep Dive",
            description = "Encounter the transformative teachings, compassion, parables, and miracles of our Savior Jesus Christ.",
            durationDays = 21,
            category = "Gospels",
            days = listOf(
                ReadingPlanDay(1, "Day 1: The Word Made Flesh", "John 1", 43, 1, 1, 18, "In the beginning was the Word. Jesus brings true divine light to every soul."),
                ReadingPlanDay(2, "Day 2: The Beatitudes & Salt of Earth", "Matthew 5", 40, 5, 1, 16, "Discover kingdom blessings and shining Christ's light before others."),
                ReadingPlanDay(3, "Day 3: The Lord's Prayer & Do Not Worry", "Matthew 6", 40, 6, 1, 34, "Learn authentic prayer and resting in our Heavenly Father's care."),
                ReadingPlanDay(4, "Day 4: The Golden Rule & Two Foundations", "Matthew 7", 40, 7, 1, 29, "Building your spiritual life upon the unshakable rock of Christ."),
                ReadingPlanDay(5, "Day 5: Nicodemus & God's Great Love", "John 3", 43, 3, 1, 21, "For God so loved the world. The promise of eternal new birth."),
                ReadingPlanDay(6, "Day 6: The Woman at the Well", "John 4", 43, 4, 1, 30, "Drinking from the well of Living Water that never runs dry."),
                ReadingPlanDay(7, "Day 7: Healing & Faith", "Matthew 8", 40, 8, 1, 27, "Jesus demonstrates authority over sickness and calm over the raging sea."),
                ReadingPlanDay(8, "Day 8: Parable of the Sower", "Matthew 13", 40, 13, 1, 23, "Cultivating a receptive heart for the seed of God's Word."),
                ReadingPlanDay(9, "Day 9: Feeding the Multitude & Walking on Water", "Matthew 14", 40, 14, 13, 33, "Jesus provides abundantly and calls us to step out in courageous faith."),
                ReadingPlanDay(10, "Day 10: The Good Samaritan", "Luke 10", 42, 10, 25, 37, "Loving our neighbor with tangible mercy, time, and sacrifice."),
                ReadingPlanDay(11, "Day 11: The Prodigal Son & Loving Father", "Luke 15", 42, 15, 11, 32, "The unbounded joy in heaven when a lost child returns home to God."),
                ReadingPlanDay(12, "Day 12: The Good Shepherd", "John 10", 43, 10, 1, 18, "Jesus lays down His life for His sheep and gives eternal security."),
                ReadingPlanDay(13, "Day 13: The Resurrection and the Life", "John 11", 43, 11, 17, 44, "Jesus has victory over death and promises everlasting life."),
                ReadingPlanDay(14, "Day 14: Washing the Disciples' Feet", "John 13", 43, 13, 1, 17, "True spiritual greatness is found in humble servant leadership."),
                ReadingPlanDay(15, "Day 15: I am the Way, Truth, and Life", "John 14", 43, 14, 1, 14, "Jesus comforts our troubled hearts and promises the Holy Spirit Helper."),
                ReadingPlanDay(16, "Day 16: The True Vine & Abiding Fruit", "John 15", 43, 15, 1, 17, "Abiding in Christ to bear lasting fruit of love and joyful obedience."),
                ReadingPlanDay(17, "Day 17: Jesus Prays for His Believers", "John 17", 43, 17, 1, 26, "The high priestly prayer of Jesus for our sanctification and unity."),
                ReadingPlanDay(18, "Day 18: Gethsemane & Total Surrender", "Matthew 26", 40, 26, 36, 56, "Not my will, but Thine be done. The Savior's immense sacrifice."),
                ReadingPlanDay(19, "Day 19: The Cross & Atonement", "Luke 23", 42, 23, 33, 49, "Father, forgive them. The debt of our sin paid in full on Calvary."),
                ReadingPlanDay(20, "Day 20: The Empty Tomb & Risen Savior", "Luke 24", 42, 24, 1, 35, "He is not here; He is risen! The victory of the empty tomb."),
                ReadingPlanDay(21, "Day 21: The Great Commission & Promise", "Matthew 28", 40, 28, 16, 20, "Go and make disciples of all nations; Lo, I am with you always.")
            )
        ),
        ReadingPlan(
            id = "plan_anxiety_peace_14",
            title = "14-Day Overcoming Anxiety & Finding Peace",
            subtitle = "Scriptural Comfort for Anxious Hearts",
            description = "Quiet your soul with targeted scripture promises designed to release worry and install God's calming peace in your daily life.",
            durationDays = 14,
            category = "Peace & Healing",
            days = listOf(
                ReadingPlanDay(1, "Day 1: Do Not Fear, I am With You", "Isaiah 41", 23, 41, 10, 10, "Fear not; for I am with thee: be not dismayed; for I am thy God."),
                ReadingPlanDay(2, "Day 2: Cast Your Burdens Upon Him", "1 Peter 5", 60, 5, 6, 11, "Casting all your care upon him; for he careth for you."),
                ReadingPlanDay(3, "Day 3: Secret Place of the Most High", "Psalms 91", 19, 91, 1, 16, "Dwelling in the shadow of the Almighty and resting under His protective wings."),
                ReadingPlanDay(4, "Day 4: Peace Passing Understanding", "Philippians 4", 50, 4, 4, 9, "Turn every anxious petition into thankful prayer before God."),
                ReadingPlanDay(5, "Day 5: The Lord is My Shepherd", "Psalms 23", 19, 23, 1, 6, "He restores your soul and leads you beside quiet waters of rest."),
                ReadingPlanDay(6, "Day 6: Take No Thought for Tomorrow", "Matthew 6", 40, 6, 25, 34, "Your Heavenly Father knows your needs and clothes the lilies."),
                ReadingPlanDay(7, "Day 7: My Peace I Give Unto You", "John 14", 43, 14, 25, 31, "Not as the world gives. Let not your heart be troubled."),
                ReadingPlanDay(8, "Day 8: God is Our Refuge & Strength", "Psalms 46", 19, 46, 1, 11, "Be still, and know that I am God. A very present help in trouble."),
                ReadingPlanDay(9, "Day 9: Renewed Strength for the Weary", "Isaiah 40", 23, 40, 28, 31, "They that wait upon the Lord shall mount up with wings as eagles."),
                ReadingPlanDay(10, "Day 10: Nothing Can Separate You from God's Love", "Romans 8", 45, 8, 31, 39, "More than conquerors through Him who loved us unconditionally."),
                ReadingPlanDay(11, "Day 11: Perfect Love Casts Out Fear", "1 John 4", 62, 4, 16, 21, "Resting in the perfect agape love of God that dispels all fear."),
                ReadingPlanDay(12, "Day 12: God's Plans for Hope & Future", "Jeremiah 29", 24, 29, 11, 14, "Thoughts of peace, and not of evil, to give you an expected end."),
                ReadingPlanDay(13, "Day 13: Come Unto Me and Find Rest", "Matthew 11", 40, 11, 28, 30, "My yoke is easy and my burden is light; rest for your souls."),
                ReadingPlanDay(14, "Day 14: He Will Never Leave or Forsake You", "Hebrews 13", 58, 13, 5, 8, "The Lord is my helper, and I will not fear what man shall do unto me.")
            )
        ),
        ReadingPlan(
            id = "plan_living_faith_7",
            title = "7-Day Living by Faith Walk",
            subtitle = "A Week of Spiritual Refreshment",
            description = "Start every morning with foundational scriptures to reignite your faith, strengthen your resolve, and inspire devotion.",
            durationDays = 7,
            category = "Spiritual Growth",
            days = listOf(
                ReadingPlanDay(1, "Day 1: What is Faith?", "Hebrews 11", 58, 11, 1, 6, "Faith is the substance of things hoped for, the evidence of things not seen."),
                ReadingPlanDay(2, "Day 2: Walking by Faith, Not by Sight", "2 Corinthians 5", 47, 5, 1, 10, "Trusting God's invisible eternal reality beyond temporary circumstances."),
                ReadingPlanDay(3, "Day 3: Faith Tested and Refined", "James 1", 59, 1, 2, 8, "The testing of your faith produces steadfast patience and maturity."),
                ReadingPlanDay(4, "Day 4: Faith That Works Through Love", "Galatians 5", 48, 5, 13, 26, "Bearing the fruit of the Spirit: love, joy, peace, longsuffering, goodness."),
                ReadingPlanDay(5, "Day 5: The Shield of Faith", "Ephesians 6", 49, 6, 10, 18, "Extinguishing all the fiery darts of doubt and standing firm in truth."),
                ReadingPlanDay(6, "Day 6: Faith to Move Mountains", "Matthew 17", 40, 17, 14, 21, "Even a mustard seed of faith in a great God yields immense results."),
                ReadingPlanDay(7, "Day 7: The Author & Finisher of Our Faith", "Hebrews 12", 58, 12, 1, 3, "Looking unto Jesus, who endured the cross for the joy set before Him.")
            )
        )
    )

    fun searchVerses(
        query: String,
        filterTestament: com.example.data.model.Testament? = null,
        translation: com.example.data.model.BibleTranslation = com.example.data.model.BibleTranslation.AMHARIC
    ): List<BibleVerse> {
        val trimmed = query.trim()
        if (trimmed.isEmpty()) return emptyList()

        val results = mutableListOf<BibleVerse>()
        val primaryMap = if (translation == com.example.data.model.BibleTranslation.AMHARIC) amharicKeyVersesMap else keyVersesMap
        val fallbackMap = if (translation == com.example.data.model.BibleTranslation.AMHARIC) keyVersesMap else amharicKeyVersesMap

        // Search through primary map first
        for ((_, verses) in primaryMap) {
            for (verse in verses) {
                val book = BibleDataProvider.getBookById(verse.bookId) ?: continue
                if (filterTestament != null && book.testament != filterTestament) continue

                if (verse.text.contains(trimmed, ignoreCase = true) ||
                    book.name.contains(trimmed, ignoreCase = true) ||
                    book.amharicName.contains(trimmed, ignoreCase = true) ||
                    "${book.name} ${verse.chapter}:${verse.verse}".contains(trimmed, ignoreCase = true) ||
                    "${book.amharicName} ${verse.chapter}:${verse.verse}".contains(trimmed, ignoreCase = true)
                ) {
                    results.add(verse)
                }
            }
        }

        // Also check fallback if primary had few matches
        if (results.size < 10) {
            for ((_, verses) in fallbackMap) {
                for (verse in verses) {
                    val book = BibleDataProvider.getBookById(verse.bookId) ?: continue
                    if (filterTestament != null && book.testament != filterTestament) continue

                    if (verse.text.contains(trimmed, ignoreCase = true) ||
                        book.name.contains(trimmed, ignoreCase = true) ||
                        book.amharicName.contains(trimmed, ignoreCase = true)
                    ) {
                        if (!results.any { it.bookId == verse.bookId && it.chapter == verse.chapter && it.verse == verse.verse }) {
                            results.add(verse)
                        }
                    }
                }
            }
        }

        // Also search through topical verses
        for (topic in topicalCollections) {
            for (verse in topic.verses) {
                val book = BibleDataProvider.getBookById(verse.bookId) ?: continue
                if (filterTestament != null && book.testament != filterTestament) continue

                if (verse.text.contains(trimmed, ignoreCase = true) ||
                    topic.topic.contains(trimmed, ignoreCase = true) ||
                    book.name.contains(trimmed, ignoreCase = true) ||
                    book.amharicName.contains(trimmed, ignoreCase = true)
                ) {
                    if (!results.any { it.bookId == verse.bookId && it.chapter == verse.chapter && it.verse == verse.verse }) {
                        results.add(verse)
                    }
                }
            }
        }

        return results.take(100)
    }
}
