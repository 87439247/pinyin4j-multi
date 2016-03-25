package net.sourceforge.pinyin4j.test;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Assert;
import org.junit.Test;

public class PinyinHelperTest {
    private static final String[] ARR_EMPTY = {};

    @Test
    public void testToTongyongPinyinStringArray() {
        // any input of non-Chinese characters will return null
        {
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toTongyongPinyinStringArray('A'));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toTongyongPinyinStringArray('z'));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toTongyongPinyinStringArray(','));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toTongyongPinyinStringArray('。'));
        }

        // Chinese characters
        // single pronounciation
        {
            String[] expectedPinyinArray = new String[] {"li3"};
            String[] pinyinArray = PinyinHelper.toTongyongPinyinStringArray('李');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);
        }
        {
            String[] expectedPinyinArray = new String[] {"ciou2"};
            String[] pinyinArray = PinyinHelper.toTongyongPinyinStringArray('球');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);
        }
        {
            String[] expectedPinyinArray = new String[] {"jhuang1"};
            String[] pinyinArray = PinyinHelper.toTongyongPinyinStringArray('桩');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);
        }

        // multiple pronounciations
        {
            String[] expectedPinyinArray = new String[] {"chuan2", "jhuan4"};
            String[] pinyinArray = PinyinHelper.toTongyongPinyinStringArray('传');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);
        }

        {
            String[] expectedPinyinArray = new String[] {"lyu4", "lu4"};
            String[] pinyinArray = PinyinHelper.toTongyongPinyinStringArray('绿');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);
        }
    }

    @Test
    public void testToWadeGilesPinyinStringArray() {
        // any input of non-Chinese characters will return null
        {
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toWadeGilesPinyinStringArray('A'));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toWadeGilesPinyinStringArray('z'));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toWadeGilesPinyinStringArray(','));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toWadeGilesPinyinStringArray('。'));
        }

        // Chinese characters
        // single pronounciation
        {
            String[] expectedPinyinArray = new String[] {"li3"};
            String[] pinyinArray = PinyinHelper.toWadeGilesPinyinStringArray('李');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }
        {
            String[] expectedPinyinArray = new String[] {"ch`iu2"};
            String[] pinyinArray = PinyinHelper.toWadeGilesPinyinStringArray('球');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }
        {
            String[] expectedPinyinArray = new String[] {"chuang1"};
            String[] pinyinArray = PinyinHelper.toWadeGilesPinyinStringArray('桩');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }

        // multiple pronounciations
        {
            String[] expectedPinyinArray = new String[] {"ch`uan2", "chuan4"};
            String[] pinyinArray = PinyinHelper.toWadeGilesPinyinStringArray('传');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }

        {
            String[] expectedPinyinArray = new String[] {"lu:4", "lu4"};
            String[] pinyinArray = PinyinHelper.toWadeGilesPinyinStringArray('绿');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }
    }

    @Test
    public void testToMPS2PinyinStringArray() {
        // any input of non-Chinese characters will return null
        {
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toMPS2PinyinStringArray('A'));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toMPS2PinyinStringArray('z'));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toMPS2PinyinStringArray(','));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toMPS2PinyinStringArray('。'));
        }

        // Chinese characters
        // single pronounciation
        {
            String[] expectedPinyinArray = new String[] {"li3"};
            String[] pinyinArray = PinyinHelper.toMPS2PinyinStringArray('李');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }
        {
            String[] expectedPinyinArray = new String[] {"chiou2"};
            String[] pinyinArray = PinyinHelper.toMPS2PinyinStringArray('球');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }
        {
            String[] expectedPinyinArray = new String[] {"juang1"};
            String[] pinyinArray = PinyinHelper.toMPS2PinyinStringArray('桩');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }

        // multiple pronounciations
        {
            String[] expectedPinyinArray = new String[] {"chuan2", "juan4"};
            String[] pinyinArray = PinyinHelper.toMPS2PinyinStringArray('传');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }

        {
            String[] expectedPinyinArray = new String[] {"liu4", "lu4"};
            String[] pinyinArray = PinyinHelper.toMPS2PinyinStringArray('绿');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }
    }

    @Test
    public void testToYalePinyinStringArray() {
        // any input of non-Chinese characters will return null
        {
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toYalePinyinStringArray('A'));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toYalePinyinStringArray('z'));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toYalePinyinStringArray(','));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toYalePinyinStringArray('。'));
        }

        // Chinese characters
        // single pronounciation
        {
            String[] expectedPinyinArray = new String[] {"li3"};
            String[] pinyinArray = PinyinHelper.toYalePinyinStringArray('李');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }
        {
            String[] expectedPinyinArray = new String[] {"chyou2"};
            String[] pinyinArray = PinyinHelper.toYalePinyinStringArray('球');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }
        {
            String[] expectedPinyinArray = new String[] {"jwang1"};
            String[] pinyinArray = PinyinHelper.toYalePinyinStringArray('桩');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }

        // multiple pronounciations
        {
            String[] expectedPinyinArray = new String[] {"chwan2", "jwan4"};
            String[] pinyinArray = PinyinHelper.toYalePinyinStringArray('传');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }

        {
            String[] expectedPinyinArray = new String[] {"lyu4", "lu4"};
            String[] pinyinArray = PinyinHelper.toYalePinyinStringArray('绿');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }
    }

    @Test
    public void testToGwoyeuRomatzyhStringArray() {
        // any input of non-Chinese characters will return null
        {
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toGwoyeuRomatzyhStringArray('A'));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toGwoyeuRomatzyhStringArray('z'));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toGwoyeuRomatzyhStringArray(','));
            Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toGwoyeuRomatzyhStringArray('。'));
        }

        // Chinese characters
        // single pronounciation
        {
            String[] expectedPinyinArray = new String[] {"lii"};
            String[] pinyinArray = PinyinHelper.toGwoyeuRomatzyhStringArray('李');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }
        {
            String[] expectedPinyinArray = new String[] {"chyou"};
            String[] pinyinArray = PinyinHelper.toGwoyeuRomatzyhStringArray('球');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }
        {
            String[] expectedPinyinArray = new String[] {"juang"};
            String[] pinyinArray = PinyinHelper.toGwoyeuRomatzyhStringArray('桩');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }

        {
            String[] expectedPinyinArray = new String[] {"fuh"};
            String[] pinyinArray = PinyinHelper.toGwoyeuRomatzyhStringArray('付');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }

        // multiple pronounciations
        {
            String[] expectedPinyinArray = new String[] {"chwan", "juann"};
            String[] pinyinArray = PinyinHelper.toGwoyeuRomatzyhStringArray('传');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }

        {
            String[] expectedPinyinArray = new String[] {".me", ".mha", "iau"};
            String[] pinyinArray = PinyinHelper.toGwoyeuRomatzyhStringArray('么');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }

        {
            String[] expectedPinyinArray = new String[] {"liuh", "luh"};
            String[] pinyinArray = PinyinHelper.toGwoyeuRomatzyhStringArray('绿');

            Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);

        }
    }

    @Test
    public void testToHanyuPinyinStringArray() {

        // any input of non-Chinese characters will return null
        {
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            try {
                Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toHanyuPinyinStringArray('A',
                        defaultFormat));
                Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toHanyuPinyinStringArray('z',
                        defaultFormat));
                Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toHanyuPinyinStringArray(',',
                        defaultFormat));
                Assert.assertArrayEquals(ARR_EMPTY, PinyinHelper.toHanyuPinyinStringArray('。',
                        defaultFormat));
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }

        // Chinese characters
        // single pronounciation
        {
            try {
                HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

                String[] expectedPinyinArray = new String[] {"li3"};
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('李', defaultFormat);

                Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }
        {
            try {
                HanyuPinyinOutputFormat upperCaseFormat = new HanyuPinyinOutputFormat();
                upperCaseFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);

                String[] expectedPinyinArray = new String[] {"LI3"};
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('李', upperCaseFormat);

                Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }
        {
            try {
                HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

                String[] expectedPinyinArray = new String[] {"lu:3"};
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('吕', defaultFormat);

                Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }
        {
            try {
                HanyuPinyinOutputFormat vCharFormat = new HanyuPinyinOutputFormat();
                vCharFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

                String[] expectedPinyinArray = new String[] {"lv3"};
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('吕', vCharFormat);

                Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }

        // multiple pronounciations
        {
            try {
                HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

                String[] expectedPinyinArray = new String[] {"jian1", "jian4"};
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('间', defaultFormat);

                Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }

        {
            try {
                HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

                String[] expectedPinyinArray = new String[] {"hao3", "hao4"};
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray('好', defaultFormat);

                Assert.assertArrayEquals(expectedPinyinArray, pinyinArray);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * test for combination of output formats
     */
    @Test
    public void testOutputCombination() {
        try {
            HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();

            // fix case type to lowercase firstly, change VChar and Tone
            // combination
            outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);

            // WITH_U_AND_COLON and WITH_TONE_NUMBER
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
            outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);

            Assert
                    .assertEquals("lu:3",
                            PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_V and WITH_TONE_NUMBER
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
            outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);

            Assert.assertEquals("lv3", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_U_UNICODE and WITH_TONE_NUMBER
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
            outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);

            Assert.assertEquals("lü3", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // // WITH_U_AND_COLON and WITHOUT_TONE
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
            outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

            Assert.assertEquals("lu:", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_V and WITHOUT_TONE
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
            outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

            Assert.assertEquals("lv", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_U_UNICODE and WITHOUT_TONE
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
            outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

            Assert.assertEquals("lü", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_U_AND_COLON and WITH_TONE_MARK is forbidden

            // WITH_V and WITH_TONE_MARK is forbidden

            // WITH_U_UNICODE and WITH_TONE_MARK
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
            outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);

            Assert.assertEquals("lǚ", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // fix case type to UPPERCASE, change VChar and Tone
            // combination
            outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);

            // WITH_U_AND_COLON and WITH_TONE_NUMBER
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
            outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);

            Assert
                    .assertEquals("LU:3",
                            PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_V and WITH_TONE_NUMBER
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
            outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);

            Assert.assertEquals("LV3", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_U_UNICODE and WITH_TONE_NUMBER
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
            outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);

            Assert.assertEquals("LÜ3", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // // WITH_U_AND_COLON and WITHOUT_TONE
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
            outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

            Assert.assertEquals("LU:", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_V and WITHOUT_TONE
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
            outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

            Assert.assertEquals("LV", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_U_UNICODE and WITHOUT_TONE
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
            outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

            Assert.assertEquals("LÜ", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);

            // WITH_U_AND_COLON and WITH_TONE_MARK is forbidden

            // WITH_V and WITH_TONE_MARK is forbidden

            // WITH_U_UNICODE and WITH_TONE_MARK
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
            outputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);

            Assert.assertEquals("LǙ", PinyinHelper.toHanyuPinyinStringArray('吕', outputFormat)[0]);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
    }
}
