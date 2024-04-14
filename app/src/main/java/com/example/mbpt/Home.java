package com.example.mbpt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {
    Button btn1;
    TextView tv1;
    ImageView iv1;
    TextView tv3;
    ImageView link1;
    ImageView link2;
    ImageView link3;
    TextView tv2;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    class dop{
        public String info;
        public String tagLine;
        public dop(){}
        public dop(String a,String b){this.info=a; this.tagLine=b;}
    }
    public Map<String,dop> map;

    public void setDataOfMap() {
        map.put("ENFJ", new dop(
                "The Advocate (ENFJ) - Outgoing, charismatic leaders who are energetic and passionate. Advocates are natural inspirers, using their gift of gag to motivate others towards a common goal. They have strong values and a deep sense of purpose, often working to improve the lives of those around them. ENFJs are highly attuned to the emotions and motivations of other people, and they use this insight to build harmonious relationships and bring people together. They have a talent for seeing the best in others and helping them reach their full potential.\n\nAdvocates are often described as 'the quintessential nurturers.' They have a genuine desire to help and support those in need, and they will tirelessly dedicate themselves to causes and individuals they care about. ENFJs excel at providing emotional support and guidance, and they take great joy in seeing others grow and succeed. However, their strong sense of altruism can also lead them to neglect their own needs at times, as they can become overly invested in the problems and concerns of the people in their lives.\n\nIn the workplace, Advocates are often drawn to roles that allow them to lead, inspire, and make a positive impact. They thrive in positions that involve coaching, mentoring, or managing teams, as they excel at bringing out the best in others. ENFJs are also skilled communicators and can be highly persuasive, making them effective in sales, marketing, or public relations roles. However, they may struggle in environments that emphasize cold, impersonal logic over emotional intelligence and interpersonal dynamics.\n\nOverall, the Advocate personality type is characterized by their warmth, charisma, and unwavering commitment to their values and the people they care about. They are the 'givers' of the MBTI world, always striving to make a difference in the lives of others.",
                "The Giver"
        ));

        map.put("ENFP", new dop(
                "The Campaigner (ENFP) - Enthusiastic, creative, and sociable personality types. Campaigners are big-picture thinkers who see potential for growth and improvement everywhere. They approach life with a childlike sense of wonder and excitement, constantly pursuing new ideas and experiences. ENFPs are driven by a desire to make a positive impact on the world, and they use their charm, creativity, and boundless energy to inspire and motivate others.\n\nCampaigners are often described as the 'free spirits' of the MBTI types. They have a natural tendency to be curious, spontaneous, and adaptable, embracing the unpredictability of life and thriving on new challenges. ENFPs are skilled at seeing the world from multiple perspectives, and they excel at generating innovative solutions to complex problems. Their boundless imagination and ability to think outside the box make them valuable assets in any creative or problem-solving endeavor.\n\nHowever, Campaigners' enthusiasm and idealism can also be a double-edged sword. Their tendency to jump from one idea to the next can make them appear scatterbrained or unreliable at times, and their strong aversion to routine and structure can make it difficult for them to follow through on long-term projects. ENFPs also have a tendency to become overly invested in the problems and concerns of others, which can lead to burnout if they don't take the time to recharge and prioritize their own needs.\n\nIn the workplace, Campaigners thrive in roles that allow them to tap into their creativity, independence, and desire to make a difference. They excel in fields such as design, marketing, education, or social work, where their ability to connect with people and generate innovative ideas can be put to good use. ENFPs may struggle, however, in highly structured or bureaucratic environments that stifle their natural tendency towards spontaneity and free-flowing expression.\n\nOverall, the Campaigner personality type is characterized by their infectious enthusiasm, boundless creativity, and deep desire to make the world a better place. They are the 'visionaries' of the MBTI world, constantly dreaming up new possibilities and inspiring those around them to reach for greatness.",
                "The Visionary"
        ));

        map.put("ENTJ", new dop(
                "The Commander (ENTJ) - Assertive and outspoken leaders who enjoy taking charge. Commanders are strategic masterminds, able to quickly identify problems and devise efficient solutions. They thrive on challenge and will not hesitate to voice their opinions, even if it means disagreeing with those in authority. ENTJs are driven by a desire to leave their mark on the world and create positive change, and they are not afraid to take bold, decisive action to achieve their goals.\n\nCommanders are often described as the 'take-charge' personalities of the MBTI types. They possess a natural leadership ability that allows them to easily rally people around a common cause and inspire them to work towards a shared vision. ENTJs are skilled at analyzing complex situations, identifying key priorities, and developing comprehensive plans to address them. Their analytical mind and problem-solving prowess make them invaluable assets in any organization or endeavor.\n\nHowever, Commanders' assertiveness and single-minded focus can also be a double-edged sword. Their tendency to be blunt and unyielding in their opinions can sometimes rub people the wrong way, and their desire for efficiency and productivity can lead them to overlook the emotional needs of those around them. ENTJs also have a tendency to become impatient and dismissive of those who do not share their drive and ambition.\n\nIn the workplace, Commanders are often drawn to leadership roles that allow them to leverage their strategic thinking and decision-making abilities. They excel in fields such as business, law, politics, or the military, where their ability to motivate teams, drive results, and make tough calls can be put to good use. ENTJs may struggle, however, in more creative or loosely structured environments that do not align with their preference for clear goals, deadlines, and measurable outcomes.\n\nOverall, the Commander personality type is characterized by their unwavering determination, analytical prowess, and relentless pursuit of success. They are the 'strategists' of the MBTI world, always looking for ways to optimize processes, streamline operations, and achieve their ambitious goals.",
                "The Strategist"
        ));

        map.put("ENTP", new dop(
                "The Debater (ENTP) - Clever, quick-witted, and intellectually curious individuals. Debaters love a good debate and will gleefully argue both sides of an issue, driven by an innate desire to uncover the truth. They are creative problem-solvers who excel at seeing things from new and innovative angles. ENTPs are driven by a thirst for knowledge and a relentless curiosity that leads them to explore a wide range of topics and ideas.\n\nDebaters are often described as the 'devil's advocates' of the MBTI types. They take great pleasure in challenging assumptions, questioning authority, and pushing the boundaries of conventional thinking. ENTPs have a natural talent for identifying flaws in arguments, spotting logical inconsistencies, and offering alternative perspectives that force others to re-evaluate their beliefs and biases. Their quick wit and verbal dexterity make them formidable debate opponents, as they are skilled at anticipating counter-arguments and responding with cutting precision.\n\nHowever, Debaters' love of intellectual discourse can also be a double-edged sword. Their tendency to constantly question and challenge can sometimes come across as confrontational or dismissive, and their impatience with those who do not share their thirst for knowledge and problem-solving can strain relationships. ENTPs also have a tendency to become so focused on the 'big picture' that they overlook important details or practical considerations.\n\nIn the workplace, Debaters thrive in roles that allow them to tap into their creativity, independence, and love of intellectual discourse. They excel in fields such as science, technology, entrepreneurship, or academia, where their ability to think outside the box, identify innovative solutions, and engage in stimulating debates can be put to good use. ENTPs may struggle, however, in highly structured or detail-oriented environments that do not align with their preference for flexibility and open-ended exploration.\n\nOverall, the Debater personality type is characterized by their quick wit, intellectual curiosity, and relentless drive to uncover the truth. They are the 'innovators' of the MBTI world, constantly pushing the boundaries of conventional thinking and offering new and exciting perspectives on the world around them.",
                "The Innovator"
        ));

        map.put("ESFJ", new dop(
                "The Consul (ESFJ) - Warm, social, and conscientious people who enjoy helping others. Consuls are dutiful caretakers, dedicated to maintaining harmony and ensuring that everyone's needs are met. They excel at reading social cues and are skilled at bringing people together. ESFJs are driven by a strong desire to create a sense of community and belonging, and they take great pride in their ability to make others feel supported and valued.\n\nConsuls are often described as the 'social butterflies' of the MBTI types. They have a natural talent for building and maintaining strong relationships, and they thrive on the energy and connection they get from interacting with others. ESFJs are skilled at anticipating the needs and preferences of those around them, and they use this insight to provide personalized support and attention. They excel at organizing events, coordinating group activities, and fostering a sense of belonging within their social circles.\n\nHowever, Consuls' strong emphasis on harmony and cooperation can also be a double-edged sword. Their desire to please others and avoid conflict can sometimes lead them to neglect their own needs or compromise their values in an effort to maintain peace. ESFJs can also become overly invested in the lives and problems of those around them, leading to burnout if they don't take the time to recharge and set boundaries.\n\nIn the workplace, Consuls are often drawn to roles that involve interacting with people, providing support and guidance, and ensuring that everyone is working together effectively. They excel in fields such as customer service, human resources, education, or social work, where their ability to build relationships, read social cues, and create a positive, collaborative environment can be put to good use. ESFJs may struggle, however, in highly competitive or individualistic environments that do not align with their preference for cooperation and teamwork.\n\nOverall, the Consul personality type is characterized by their warmth, social skills, and strong sense of duty. They are the 'caretakers' of the MBTI world, always striving to create a sense of community and ensure that everyone feels valued and supported.",
                "The Caretaker"
        ));

        map.put("ESFP", new dop(
                "The Entertainer (ESFP) - Outgoing, spontaneous, and energetic personality types. Entertainers live in the moment, driven by a desire for exciting experiences and new sensations. They are natural performers who love being the center of attention and bringing joy to those around them. ESFPs have a contagious zest for life, and they excel at creating a sense of fun and excitement wherever they go.\n\nEntertainers are often described as the 'life of the party' of the MBTI types. They have a natural talent for reading social cues and adapting their behavior to the needs and preferences of those around them, making them skilled at putting people at ease and creating a warm, welcoming atmosphere. ESFPs excel at improvising and thinking on their feet, which allows them to navigate social situations with ease and keep those around them entertained and engaged.\n\nHowever, Entertainers' love of spontaneity and their tendency to live in the moment can also be a double-edged sword. Their impulsive nature and dislike of routine can sometimes lead them to neglect practical considerations or long-term planning, which can cause problems in their personal and professional lives. ESFPs can also become overly reliant on external validation and attention, which can make them feel lost or unfulfilled if they don't receive the constant stimulation and affirmation they crave.\n\nIn the workplace, Entertainers thrive in roles that allow them to tap into their creativity, social skills, and love of excitement. They excel in fields such as performing arts, event planning, hospitality, or sales, where their ability to connect with people, think on their feet, and create a lively, engaging atmosphere can be put to good use. ESFPs may struggle, however, in highly structured or detail-oriented environments that do not align with their preference for spontaneity and flexibility.\n\nOverall, the Entertainer personality type is characterized by their infectious enthusiasm, natural charisma, and love of living in the moment. They are the 'performers' of the MBTI world, always striving to bring joy, excitement, and a sense of adventure to those around them.",
                "The Performer"
        ));

        map.put("ESTJ", new dop(
                "The Executive (ESTJ) - Practical, organized, and decisive individuals who enjoy being in charge. Executives are natural leaders, with a strong sense of duty and a commitment to upholding tradition and rules. They are excellent at managing projects and people, and thrive on efficiency and productivity. ESTJs are driven by a desire to create order, structure, and stability in their personal and professional lives.\n\nExecutives are often described as the 'take-charge' personalities of the MBTI types. They have a natural talent for identifying problems, setting clear goals, and developing comprehensive plans to address them. ESTJs excel at delegating tasks, holding people accountable, and ensuring that deadlines are met and tasks are completed efficiently. Their strong sense of responsibility and commitment to excellence make them invaluable assets in any organization or endeavor.\n\nHowever, Executives' emphasis on rules, tradition, and efficiency can also be a double-edged sword. Their tendency to be rigid and unyielding in their beliefs and decision-making can sometimes alienate those who do not share their values or approach. ESTJs can also become overly critical or dismissive of those who do not meet their high standards, which can strain relationships and undermine team morale.\n\nIn the workplace, Executives thrive in roles that allow them to leverage their leadership skills, organizational abilities, and attention to detail. They excel in fields such as management, operations, finance, or law, where their ability to create structure, streamline processes, and ensure compliance with rules and regulations can be put to good use. ESTJs may struggle, however, in more creative or adaptable environments that do not align with their preference for clear, defined goals and procedures.\n Overall, the Executive personality type is characterized by their unwavering commitment to duty, their strong organizational skills, and their desire to create order and stability in their personal and professional lives. They are the 'supervisors' of the MBTI world, always striving to ensure that tasks are completed efficiently and that everyone is working towards a common goal.",
                "The Supervisor"
        ));

        map.put("ESTP", new dop(
                "The Entrepreneur (ESTP) - Energetic, pragmatic, and action-oriented people. Entrepreneurs are masters of the immediate moment, quickly sizing up a situation and taking decisive action. They are skilled problem-solvers who are not afraid to take risks, and they excel at hands-on, practical tasks. ESTPs have a natural talent for adapting to changing circumstances and thinking on their feet, which allows them to navigate the world with agility and confidence.\n\nEntrepreneurs are often described as the 'thrill-seekers' of the MBTI types. They have a strong drive for excitement and a love of novelty, and they are constantly seeking out new challenges and experiences that will keep them engaged and stimulated. ESTPs excel at identifying opportunities, weighing the pros and cons, and taking swift, decisive action to capitalize on them. Their ability to think quickly, problem-solve on the fly, and take calculated risks makes them well-suited for fast-paced, high-pressure environments.\n\nHowever, Entrepreneurs' love of excitement and their tendency to live in the moment can also be a double-edged sword. Their impulsive nature and disregard for long-term planning can sometimes lead them to overlook important details or make decisions that have negative consequences down the line. ESTPs can also struggle to sit still and focus on more mundane tasks or responsibilities, which can cause friction in their personal and professional lives.\n\nIn the workplace, Entrepreneurs thrive in roles that allow them to tap into their energy, independence, and problem-solving skills. They excel in fields such as entrepreneurship, sales, law enforcement, or the skilled trades, where their ability to think quickly, adapt to changing circumstances, and take decisive action can be put to good use. ESTPs may struggle, however, in highly structured or detail-oriented environments that do not align with their preference for flexibility and spontaneity.\n Overall, the Entrepreneur personality type is characterized by their boundless energy, their love of excitement and risk-taking, and their ability to think quickly and adapt to changing circumstances. They are the 'operators' of the MBTI world, always seeking out new opportunities and taking bold, decisive action to seize them.",
                "The Operator"
        ));

        map.put("INFJ", new dop(
                "The Counselor (INFJ) - Introspective, idealistic, and compassionate individuals. Counselors possess a deep understanding of the human condition and a strong desire to help others. They are often described as 'the rarest personality type,' as they combine a rich inner world with a gift for interpreting the emotions and motivations of those around them. INFJs are driven by a strong moral compass and a desire to create a more just and harmonious world.\n\nCounselors are deeply empathetic individuals who have a remarkable ability to intuit the thoughts and feelings of others. They possess a keen sensitivity to the needs and concerns of those around them, and they use this insight to provide emotional support, guidance, and a listening ear. INFJs are often drawn to roles that allow them to make a positive impact on the world, such as counseling, social work, or human rights advocacy.\n\nHowever, Counselors' deep sensitivity and strong idealism can also be a double-edged sword. Their tendency to internalize the problems and emotions of others can lead to burnout if they do not take the time to recharge and set boundaries. INFJs can also become disillusioned or frustrated when their lofty visions for a better world are not realized, and they may struggle to reconcile their ideals with the realities of everyday life.\n\nIn the workplace, Counselors thrive in roles that allow them to tap into their empathy, intuition, and desire to make a difference. They excel in fields such as counseling, social work, education, or the non-profit sector, where their ability to connect with others, offer guidance and support, and work towards a greater good can be put to good use. INFJs may struggle, however, in highly competitive or cutthroat environments that do not align with their preference for cooperation and harmony.\n\nOverall, the Counselor personality type is characterized by their deep compassion, their strong moral compass, and their unwavering commitment to creating a better world. They are the 'protectors' of the MBTI world, always striving to use their remarkable insight and empathy to uplift and support those around them.",
                "The Protector"
        ));

        map.put("INFP", new dop(
                "The Healer (INFP) - Thoughtful, introspective, and idealistic personality types. Healers are driven by a strong moral compass and a desire to create a more just and harmonious world. They are skilled at empathizing with others and offering a sympathetic ear, but can also be highly imaginative and creative. INFPs possess a rich inner world and a deep appreciation for the beauty and complexity of human experience.\n\nHealers are often described as the 'idealists' of the MBTI types. They have a strong sense of personal values and a deep commitment to authenticity, and they are driven by a desire to make a positive impact on the world. INFPs excel at seeing the potential for growth and improvement in themselves and others, and they use their creativity and empathy to explore new ways of addressing societal issues and promoting individual well-being.\n\nHowever, Healers' idealism and sensitivity can also be a double-edged sword. Their tendency to become deeply invested in the problems and concerns of others can lead to burnout if they do not take the time to recharge and prioritize their own needs. INFPs can also struggle with decisiveness and assertiveness, as they often agonize over making choices that could hurt or disappoint those around them.\n\nIn the workplace, Healers thrive in roles that allow them to tap into their creativity, compassion, and desire to make a difference. They excel in fields such as the arts, education, social work, or non-profit organizations, where their ability to empathize, offer emotional support, and work towards a greater good can be put to good use. INFPs may struggle, however, in highly competitive or fast-paced environments that do not align with their preference for introspection, thoughtfulness, and a slower, more deliberate pace.\n Overall, the Healer personality type is characterized by their deep compassion, their unwavering idealism, and their desire to create a more just and harmonious world.They are the 'dreamers' of the MBTI world, constantly seeking new ways to make a positive impact and bringing their unique blend of empathy, creativity, and sensitivity to every aspect of their lives. ",
                "The Dreamer"
        ));

        map.put("INTJ", new dop(
                "The Architect (INTJ) - Analytical, strategic, and independent individuals. Architects are masterful planners, able to envision complex systems and structures in their mind's eye. They are logical, objective thinkers who relentlessly pursue knowledge and understanding, and they are not afraid to challenge the status quo. INTJs are driven by a desire to leave their mark on the world and create positive change through innovative solutions and well-crafted strategies.\n\nArchitects are often described as the 'masterminds' of the MBTI types. They possess a remarkable ability to analyze complex problems, identify key priorities, and develop comprehensive plans to address them. INTJs excel at seeing the big picture, anticipating potential obstacles, and devising efficient, innovative solutions that optimize resources and maximize results. Their logical, objective approach to problem-solving allows them to make tough decisions and navigate challenging situations with confidence and clarity.\n\nHowever, Architects' analytical, independent nature can also be a double-edged sword. Their tendency to be blunt, critical, and dismissive of opinions that do not align with their own can sometimes alienate those around them, particularly those who prefer a more collaborative or consensus-driven approach. INTJs can also become so focused on their long-term vision and strategic planning that they overlook important details or fail to consider the emotional needs and concerns of those they work with.\n In the workplace, Architects thrive in roles that allow them to leverage their strategic thinking, problem - solving skills, and innovative mindset.They excel in fields such as science, technology, engineering, business, or academia, where their ability to identify complex problems, devise creative solutions, and drive projects to successful completion can be put to good use.INTJs may struggle, however, in environments that emphasize conformity, tradition, or interpersonal harmony over individual achievement and intellectual rigor.\n Overall, the Architect personality type is characterized by their unwavering determination, their analytical prowess, and their relentless pursuit of knowledge and understanding.They are the 'masterminds' of the MBTI world, always seeking to optimize processes, streamline operations, and leave a lasting impact on the world around them. ",
                "The Mastermind"
        ));

        map.put("INTP", new dop(
                "The Logician (INTP) - Curious, intellectual, and analytical personality types. Logicians are deep, complex thinkers who love delving into abstract concepts and complex theories. They are skilled at identifying patterns and connections, and they approach problems with a detached, impartial perspective. INTPs are driven by a relentless curiosity and a desire to uncover the underlying truths and principles that govern the world around them.\n\nLogicians are often described as the 'philosophers' of the MBTI types. They possess a keen intellect and a love of analysis that leads them to explore a wide range of topics and ideas. INTPs excel at spotting logical inconsistencies, questioning assumptions, and challenging conventional wisdom. They thrive on the process of uncovering new information, identifying patterns, and developing innovative solutions to complex problems.\n\nHowever, Logicians' intellectual focus and detachment can also be a double-edged sword. Their tendency to be highly critical and analytical can sometimes make them appear aloof, distant, and even insensitive to the emotional needs and concerns of those around them. INTPs can also become so absorbed in their own intellectual pursuits that they neglect practical considerations or fail to follow through on their ideas and plans.\n In the workplace, Logicians thrive in roles that allow them to tap into their analytical skills, intellectual curiosity, and problem - solving prowess.They excel in fields such as science, technology, research, or academia, where their ability to think critically, identify patterns, and develop innovative solutions can be put to good use.INTPs may struggle, however, in environments that emphasize teamwork, emotional intelligence, or strict adherence to procedures and protocols.\n Overall, the Logician personality type is characterized by their insatiable intellectual curiosity, their analytical prowess, and their desire to uncover the underlying truths and principles that govern the world around them.They are the 'thinkers' of the MBTI world, always seeking to expand their understanding and push the boundaries of what is known. ",
                "The Thinker"
        ));

        map.put("ISFJ", new dop(
                "The Defender (ISFJ) - Dependable, practical, and detail-oriented individuals. Defenders are the backbone of many organizations, quietly and conscientiously ensuring that tasks are completed and responsibilities are met. They are driven by a strong sense of duty and a desire to support and nurture those around them. ISFJs possess a remarkable attention to detail and a talent for anticipating the needs and preferences of others, making them invaluable assets in a wide range of settings.\n\nDefenders are often described as the 'quiet heroes' of the MBTI types. They have a natural tendency to put the needs of others before their own, and they take great pride in their ability to provide reliable, dependable support. ISFJs excel at gathering and organizing information, following established procedures, and ensuring that everything runs smoothly and efficiently. Their unwavering commitment to their responsibilities and their attention to detail make them highly valued team members and trusted advisors.\n\nHowever, Defenders' strong sense of duty and desire to please others can also be a double-edged sword. Their tendency to prioritize the needs of others over their own can lead to burnout if they do not take the time to recharge and set boundaries. ISFJs can also struggle with assertiveness and confronting difficult situations, preferring to avoid conflict and maintain harmony at all costs.\n In the workplace, Defenders thrive in roles that allow them to leverage their organizational skills, attention to detail, and service - oriented mindset.They excel in fields such as administration, customer service, healthcare, or education, where their ability to anticipate needs, follow procedures, and provide reliable, personalized support can be put to good use.ISFJs may struggle, however, in highly innovative or fast - paced environments that do not align with their preference for stability, routine, and incremental progress.\n Overall, the Defender personality type is characterized by their unwavering dedication, their attention to detail, and their desire to support and nurture those around them.They are the 'protectors' of the MBTI world, always striving to ensure that tasks are completed efficiently and that everyone feels valued and supported. ",
                "The Protector"
        ));

        map.put("ISFP", new dop(
                "The Adventurer (ISFP) - Easygoing, spontaneous, and artistic personality types. Adventurers live in the moment, driven by a desire for sensory experiences and a deep appreciation for beauty in all its forms. They are gentle, compassionate souls who prefer to work behind the scenes, but they possess a remarkable talent for creative self-expression. ISFPs have a natural tendency to embrace the unpredictability of life and find joy in the simple pleasures that the world has to offer.\n\nAdventurers are often described as the 'artists' of the MBTI types. They have a keen eye for aesthetics and a deep appreciation for the beauty that can be found in the world around them. ISFPs excel at using their creativity to express their emotions, experiences, and perspectives, whether through painting, music, poetry, or any other form of artistic expression. They possess a remarkable ability to connect with others on a deep, emotional level and to create works that resonate with the human experience.\n\nHowever, Adventurers' easygoing nature and preference for spontaneity can also be a double-edged sword. Their dislike of structure and routine can sometimes lead them to neglect practical considerations or long-term planning, which can cause problems in their personal and professional lives. ISFPs can also struggle with assertiveness and confronting difficult situations, preferring to avoid conflict and maintain a sense of harmony in their relationships.\n In the workplace, Adventurers thrive in roles that allow them to tap into their creativity, sensory awareness, and love of beauty.They excel in fields such as the arts, design, or environmental conservation, where their ability to see the world through a unique lens and express themselves through their work can be put to good use.ISFPs may struggle, however, in highly structured or competitive environments that do not align with their preference for flexibility and self - expression.\n Overall, the Adventurer personality type is characterized by their easygoing nature, their deep appreciation for beauty, and their desire to embrace the spontaneity and unpredictability of life.They are the 'artists' of the MBTI world, always seeking to express themselves and connect with others through their creative endeavors.",
                "The Artist"
        ));
        map.put("ISTP", new dop(
                "The Virtuoso (ISTP) - Analytical, practical, and adventurous individuals. Virtuosos are skilled at understanding how things work and are adept at troubleshooting and problem-solving. They have a natural curiosity and enjoy exploring the world around them, often through hands-on experimentation and exploration. ISTPs thrive on challenge and excitement, and they are always eager to take on new experiences and push the boundaries of what is possible.\n\nVirtuosos are often described as the 'craftspeople' of the MBTI types. They have a keen mechanical aptitude and a talent for working with their hands, whether it's fixing a broken appliance or building something from scratch. ISTPs excel at understanding complex systems and are skilled at finding practical solutions to real-world problems. Their ability to stay calm and composed under pressure makes them invaluable in high-stress situations.\n\nHowever, Virtuosos' preference for independence and freedom can sometimes make them appear aloof or detached from others. Their focus on practicality and efficiency can also lead them to prioritize tasks over relationships, which can sometimes cause tension in their personal and professional lives. ISTPs may struggle with long-term planning or following through on commitments, as they prefer to live in the moment and adapt to changing circumstances as they arise.\n\nIn the workplace, Virtuosos thrive in roles that allow them to leverage their analytical skills, practical mindset, and love of adventure. They excel in fields such as engineering, mechanics, technology, or outdoor pursuits, where their ability to think on their feet and solve problems in real-time can be put to good use. ISTPs may struggle, however, in highly structured or bureaucratic environments that do not allow for flexibility or autonomy.\n\nOverall, the Virtuoso personality type is characterized by their analytical prowess, their practical mindset, and their adventurous spirit. They are the 'craftspeople' of the MBTI world, always seeking to understand how things work and pushing the boundaries of what is possible through hands-on experimentation and exploration.",
                "The Craftsman"
        ));

        map.put("ISTJ", new dop(
                "The Logistician (ISTJ) - Practical, pragmatic, and detail-oriented individuals. Logisticians are the backbone of many organizations, ensuring that tasks are completed efficiently and effectively. They are driven by a strong sense of duty and responsibility, and they take pride in their ability to uphold traditions and standards. ISTJs excel at gathering and organizing information, following established procedures, and ensuring that everything runs smoothly and according to plan.\n\nLogisticians are often described as the 'guardians' of the MBTI types. They have a natural tendency to prioritize stability and reliability, and they take their commitments and responsibilities very seriously. ISTJs are highly dependable team members who can always be counted on to deliver results and meet deadlines. Their attention to detail and practical mindset make them invaluable assets in a wide range of settings.\n\nHowever, Logisticians' preference for structure and routine can sometimes make them resistant to change or new ideas. Their focus on tradition and adherence to established norms can also make them inflexible or unwilling to consider alternative approaches. ISTJs may struggle with creativity or innovation, preferring to rely on proven methods and strategies rather than experimenting with new ones.\n\nIn the workplace, Logisticians thrive in roles that allow them to leverage their organizational skills, attention to detail, and practical mindset. They excel in fields such as administration, finance, engineering, or the military, where their ability to follow procedures, solve problems, and ensure that everything runs smoothly can be put to good use. ISTJs may struggle, however, in highly unstructured or chaotic environments that do not provide clear guidelines or require them to deviate from established norms and routines.\n\nOverall, the Logistician personality type is characterized by their practicality, reliability, and attention to detail. They are the 'guardians' of the MBTI world, always working behind the scenes to ensure that everything runs smoothly and according to plan.",
                "The Guardian"
        ));

    }
    protected void getId(){
        btn1=findViewById(R.id.btn1);
        tv1=findViewById(R.id.tv1);
        iv1=findViewById(R.id.iv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        link1=findViewById(R.id.link1);
        link2=findViewById(R.id.link2);
        link3=findViewById(R.id.link3);
        map=new HashMap<>();
    }
    public void setImage(String ptype){
        iv1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.background));

        if(ptype.equals("enfj")) iv1.setImageResource(R.drawable.enfj);
        if(ptype.equals("enfp")) iv1.setImageResource(R.drawable.enfp);
        if(ptype.equals("entj")) iv1.setImageResource(R.drawable.entj);
        if(ptype.equals("entp")) iv1.setImageResource(R.drawable.entp);
        if(ptype.equals("esfj")) iv1.setImageResource(R.drawable.esfj);
        if(ptype.equals("esfp")) iv1.setImageResource(R.drawable.esfp);
        if(ptype.equals("estj")) iv1.setImageResource(R.drawable.estj);
        if(ptype.equals("estp")) iv1.setImageResource(R.drawable.estp);
        if(ptype.equals("infj")) iv1.setImageResource(R.drawable.infj);
        if(ptype.equals("infp")) iv1.setImageResource(R.drawable.infp);
        if(ptype.equals("intj")) iv1.setImageResource(R.drawable.intj);
        if(ptype.equals("intp")) iv1.setImageResource(R.drawable.intp);
        if(ptype.equals("isfj")) iv1.setImageResource(R.drawable.isfj);
        if(ptype.equals("isfp")) iv1.setImageResource(R.drawable.isfp);
        if(ptype.equals("istj")) iv1.setImageResource(R.drawable.istj);
        if(ptype.equals("istp")) iv1.setImageResource(R.drawable.istp);
    }

        public String name="";
        public String uid="";
        public String type="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getId();
        //
        setDataOfMap();
        Intent intent = getIntent();
        String source = intent.getStringExtra("source");
        if (source != null && source.equals("signIn")) {
            name =intent.getStringExtra("name");
            uid=intent.getStringExtra("uid");
        }else if(source !=null && source.equals("register")){
            name =intent.getStringExtra("name");
            uid=intent.getStringExtra("uid");
        }

        // taking data from the firebase
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("userPersonality");
        databaseReference.child(uid).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {

            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    personalityData pd = dataSnapshot.getValue(personalityData.class);// set the information and data about personality
                    //making changes in the ui here
                    Log.d("pd details",map.get(pd.personalityType).info);

                    setImage(pd.personalityType.toLowerCase());
                    tv3.setText(map.get(pd.personalityType).info);
                    tv1.setText(name+"\n"+uid+"\n\n"+pd.personalityType.toUpperCase()+"\n"+map.get(pd.personalityType).tagLine);

                }else{
                    Toast.makeText(getApplicationContext(),"Failed to get any previous personality test's result",Toast.LENGTH_SHORT).show();
                    tv2.setText("Give Test !!!");
                    tv3.setText("Please give the test to see the results");

                }
            }
        });


        if (source != null && source.equals("test")) {
            type = intent.getStringExtra("personalityType");
        }

        setDataOfMap();
        tv1.setText(name.toUpperCase()+"\n"+uid+"\n");




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, signin.class);
                startActivity(i);
            }
        });
        final String uidCopy=uid;
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this, test.class);
                i.putExtra("uid",uidCopy);
                startActivity(i);

            }
        });

        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this, github.class);
                startActivity(i);
            }
        });
        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this, linkedin.class);
                startActivity(i);
            }
        });
        link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this, insta.class);
                startActivity(i);
            }
        });



    }
}