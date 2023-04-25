PGDMP                         {           essentls    15.2    15.2 :    T           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            U           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            V           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            W           1262    24577    essentls    DATABASE     �   CREATE DATABASE essentls WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United Kingdom.1252';
    DROP DATABASE essentls;
                essentls    false            V           1247    24594    diet    TYPE     �   CREATE TYPE public.diet AS ENUM (
    'No specific',
    'Vegetarian',
    'Vegan',
    'Halal',
    'Kosher',
    'Pescatarian'
);
    DROP TYPE public.diet;
       public          postgres    false            P           1247    24579    gen    TYPE     K   CREATE TYPE public.gen AS ENUM (
    'female',
    'male',
    'others'
);
    DROP TYPE public.gen;
       public          postgres    false            S           1247    24586    identity    TYPE     X   CREATE TYPE public.identity AS ENUM (
    'ID',
    'Passport',
    'Driver license'
);
    DROP TYPE public.identity;
       public          postgres    false            _           1247    24633 	   paymethod    TYPE     M   CREATE TYPE public.paymethod AS ENUM (
    'Cash',
    'Card',
    'Bank'
);
    DROP TYPE public.paymethod;
       public          postgres    false            k           1247    24673 	   roletypes    TYPE     q   CREATE TYPE public.roletypes AS ENUM (
    'Organizer',
    'Participant',
    'Volunteer',
    'WaitingList'
);
    DROP TYPE public.roletypes;
       public          postgres    false            �            1259    24661    Causes    TABLE     [   CREATE TABLE public."Causes" (
    id integer NOT NULL,
    name character varying(255)
);
    DROP TABLE public."Causes";
       public         heap    postgres    false            �            1259    24660    Causes_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Causes_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Causes_id_seq";
       public          postgres    false    221            X           0    0    Causes_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Causes_id_seq" OWNED BY public."Causes".id;
          public          postgres    false    220            �            1259    24713    EventCauses    TABLE     f   CREATE TABLE public."EventCauses" (
    "eventId" integer NOT NULL,
    "causeId" integer NOT NULL
);
 !   DROP TABLE public."EventCauses";
       public         heap    postgres    false            �            1259    24698 	   EventTags    TABLE     m   CREATE TABLE public."EventTags" (
    "eventId" integer NOT NULL,
    tag character varying(255) NOT NULL
);
    DROP TABLE public."EventTags";
       public         heap    postgres    false            �            1259    24622    Events    TABLE     �  CREATE TABLE public."Events" (
    id integer NOT NULL,
    name text NOT NULL,
    description text,
    price numeric(10,2),
    visibility integer DEFAULT 3,
    location json,
    "maxParticipantsInternational" integer,
    "maxParticipantsVolunteer" integer,
    "eventStart" timestamp without time zone,
    "eventEnd" timestamp without time zone,
    "subscriptionStart" timestamp without time zone,
    "subscriptionEnd" timestamp without time zone,
    "withdrawalEnd" timestamp without time zone,
    "maxWaitingList" integer,
    attributes text[],
    thumbnail text,
    poster text,
    CONSTRAINT "Events_price_check" CHECK ((price >= (0)::numeric))
);
    DROP TABLE public."Events";
       public         heap    postgres    false            �            1259    24621    Events_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Events_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Events_id_seq";
       public          postgres    false    217            Y           0    0    Events_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Events_id_seq" OWNED BY public."Events".id;
          public          postgres    false    216            �            1259    24681    Participants    TABLE     �   CREATE TABLE public."Participants" (
    "userId" integer NOT NULL,
    "eventId" integer NOT NULL,
    role public.roletypes,
    date timestamp without time zone,
    "attributeValues" json
);
 "   DROP TABLE public."Participants";
       public         heap    postgres    false    875            �            1259    24640    Payments    TABLE     8  CREATE TABLE public."Payments" (
    id integer NOT NULL,
    "userId" integer NOT NULL,
    "eventId" integer,
    method public.paymethod,
    amount numeric(10,2) NOT NULL,
    date date DEFAULT CURRENT_DATE NOT NULL,
    notes text,
    CONSTRAINT "Payments_amount_check" CHECK ((amount >= (0)::numeric))
);
    DROP TABLE public."Payments";
       public         heap    postgres    false    863            �            1259    24639    Payments_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Payments_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."Payments_id_seq";
       public          postgres    false    219            Z           0    0    Payments_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public."Payments_id_seq" OWNED BY public."Payments".id;
          public          postgres    false    218            �            1259    24667    Tags    TABLE     I   CREATE TABLE public."Tags" (
    name character varying(255) NOT NULL
);
    DROP TABLE public."Tags";
       public         heap    postgres    false            �            1259    24608    Users    TABLE     �  CREATE TABLE public."Users" (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    "cardID" character varying(50),
    tier integer DEFAULT 0,
    "registrationDate" date,
    name character varying(50),
    surname character varying(50),
    sex public.gen,
    "dateOfBirth" date,
    nationality character varying(100),
    "homeCountryAddress" json,
    "homeCountryUniversity" character varying(150),
    "periodOfStay" integer,
    "phoneNumber" character varying(50),
    "paduaAddress" json,
    "documentType" public.identity,
    "documentNumber" character varying(50),
    "documentFile" text,
    "dietType" public.diet,
    allergies text[],
    "emailHash" character varying(255),
    "emailConfirmed" boolean DEFAULT false,
    CONSTRAINT "Users_email_check" CHECK (((email)::text ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'::text))
);
    DROP TABLE public."Users";
       public         heap    postgres    false    848    851    854            �            1259    24607    Users_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Users_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public."Users_id_seq";
       public          postgres    false    215            [           0    0    Users_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public."Users_id_seq" OWNED BY public."Users".id;
          public          postgres    false    214            �           2604    24664 	   Causes id    DEFAULT     j   ALTER TABLE ONLY public."Causes" ALTER COLUMN id SET DEFAULT nextval('public."Causes_id_seq"'::regclass);
 :   ALTER TABLE public."Causes" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220    221            �           2604    24625 	   Events id    DEFAULT     j   ALTER TABLE ONLY public."Events" ALTER COLUMN id SET DEFAULT nextval('public."Events_id_seq"'::regclass);
 :   ALTER TABLE public."Events" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            �           2604    24643    Payments id    DEFAULT     n   ALTER TABLE ONLY public."Payments" ALTER COLUMN id SET DEFAULT nextval('public."Payments_id_seq"'::regclass);
 <   ALTER TABLE public."Payments" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218    219            �           2604    24611    Users id    DEFAULT     h   ALTER TABLE ONLY public."Users" ALTER COLUMN id SET DEFAULT nextval('public."Users_id_seq"'::regclass);
 9   ALTER TABLE public."Users" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215            M          0    24661    Causes 
   TABLE DATA           ,   COPY public."Causes" (id, name) FROM stdin;
    public          postgres    false    221   �F       Q          0    24713    EventCauses 
   TABLE DATA           =   COPY public."EventCauses" ("eventId", "causeId") FROM stdin;
    public          postgres    false    225   G       P          0    24698 	   EventTags 
   TABLE DATA           5   COPY public."EventTags" ("eventId", tag) FROM stdin;
    public          postgres    false    224   6G       I          0    24622    Events 
   TABLE DATA             COPY public."Events" (id, name, description, price, visibility, location, "maxParticipantsInternational", "maxParticipantsVolunteer", "eventStart", "eventEnd", "subscriptionStart", "subscriptionEnd", "withdrawalEnd", "maxWaitingList", attributes, thumbnail, poster) FROM stdin;
    public          postgres    false    217   mG       O          0    24681    Participants 
   TABLE DATA           \   COPY public."Participants" ("userId", "eventId", role, date, "attributeValues") FROM stdin;
    public          postgres    false    223   I       K          0    24640    Payments 
   TABLE DATA           Z   COPY public."Payments" (id, "userId", "eventId", method, amount, date, notes) FROM stdin;
    public          postgres    false    219   �I       N          0    24667    Tags 
   TABLE DATA           &   COPY public."Tags" (name) FROM stdin;
    public          postgres    false    222   6J       G          0    24608    Users 
   TABLE DATA           P  COPY public."Users" (id, email, password, "cardID", tier, "registrationDate", name, surname, sex, "dateOfBirth", nationality, "homeCountryAddress", "homeCountryUniversity", "periodOfStay", "phoneNumber", "paduaAddress", "documentType", "documentNumber", "documentFile", "dietType", allergies, "emailHash", "emailConfirmed") FROM stdin;
    public          postgres    false    215   iJ       \           0    0    Causes_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Causes_id_seq"', 5, true);
          public          postgres    false    220            ]           0    0    Events_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Events_id_seq"', 5, true);
          public          postgres    false    216            ^           0    0    Payments_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public."Payments_id_seq"', 5, true);
          public          postgres    false    218            _           0    0    Users_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public."Users_id_seq"', 5, true);
          public          postgres    false    214            �           2606    24666    Causes Causes_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Causes"
    ADD CONSTRAINT "Causes_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Causes" DROP CONSTRAINT "Causes_pkey";
       public            postgres    false    221            �           2606    24717    EventCauses EventCauses_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."EventCauses"
    ADD CONSTRAINT "EventCauses_pkey" PRIMARY KEY ("eventId", "causeId");
 J   ALTER TABLE ONLY public."EventCauses" DROP CONSTRAINT "EventCauses_pkey";
       public            postgres    false    225    225            �           2606    24702    EventTags EventTags_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public."EventTags"
    ADD CONSTRAINT "EventTags_pkey" PRIMARY KEY ("eventId", tag);
 F   ALTER TABLE ONLY public."EventTags" DROP CONSTRAINT "EventTags_pkey";
       public            postgres    false    224    224            �           2606    24631    Events Events_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "Events_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Events" DROP CONSTRAINT "Events_pkey";
       public            postgres    false    217            �           2606    24687    Participants Participants_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT "Participants_pkey" PRIMARY KEY ("userId", "eventId");
 L   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT "Participants_pkey";
       public            postgres    false    223    223            �           2606    24649    Payments Payments_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT "Payments_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT "Payments_pkey";
       public            postgres    false    219            �           2606    24671    Tags Tags_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Tags"
    ADD CONSTRAINT "Tags_pkey" PRIMARY KEY (name);
 <   ALTER TABLE ONLY public."Tags" DROP CONSTRAINT "Tags_pkey";
       public            postgres    false    222            �           2606    24620    Users Users_email_key 
   CONSTRAINT     U   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_email_key" UNIQUE (email);
 C   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_email_key";
       public            postgres    false    215            �           2606    24618    Users Users_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY (id);
 >   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_pkey";
       public            postgres    false    215            �           2606    24723    EventCauses fk_cause    FK CONSTRAINT     �   ALTER TABLE ONLY public."EventCauses"
    ADD CONSTRAINT fk_cause FOREIGN KEY ("causeId") REFERENCES public."Causes"(id) ON DELETE SET NULL;
 @   ALTER TABLE ONLY public."EventCauses" DROP CONSTRAINT fk_cause;
       public          postgres    false    3239    221    225            �           2606    24655    Payments fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 =   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_event;
       public          postgres    false    219    3235    217            �           2606    24693    Participants fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 A   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT fk_event;
       public          postgres    false    223    217    3235            �           2606    24703    EventTags fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."EventTags"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 >   ALTER TABLE ONLY public."EventTags" DROP CONSTRAINT fk_event;
       public          postgres    false    224    217    3235            �           2606    24718    EventCauses fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."EventCauses"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 @   ALTER TABLE ONLY public."EventCauses" DROP CONSTRAINT fk_event;
       public          postgres    false    3235    217    225            �           2606    24708    EventTags fk_tag    FK CONSTRAINT     �   ALTER TABLE ONLY public."EventTags"
    ADD CONSTRAINT fk_tag FOREIGN KEY (tag) REFERENCES public."Tags"(name) ON DELETE SET NULL;
 <   ALTER TABLE ONLY public."EventTags" DROP CONSTRAINT fk_tag;
       public          postgres    false    224    3241    222            �           2606    24650    Payments fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_user FOREIGN KEY ("userId") REFERENCES public."Users"(id) ON DELETE SET NULL;
 <   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_user;
       public          postgres    false    3233    219    215            �           2606    24688    Participants fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT fk_user FOREIGN KEY ("userId") REFERENCES public."Users"(id) ON DELETE SET NULL;
 @   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT fk_user;
       public          postgres    false    3233    215    223            M   6   x�3�tN,-NU��K�2��C��a쌢�T.(�-������,K����� ȯ�      Q      x������ � �      P   '   x�3�ILWp�,K�2�0�K���Й!E��\1z\\\ `Ge      I   �  x�͔Oo�0���S��C�r5�i��4�v�[�]��,�컯T��`��\�/�˓�[nk^(���`���י��&|Y)kQ�� ��'!��L�ڵ���\5`.�?e��U�(A�!�!Dz����f��P�p�GV�a:�6��O7ʔ*�b�x�˂�6P�+ݳn�M�y��$���ْW�Zm�E��:z�,{+6�R�l^{�hQ��Xl�՘�WY���3�en��@02�MB]�F,�$��n�fv���j�����1y`�D�E��|/,����23��WL|B��j���$�ԭKܬ�O|&G�wr[::�����G̈́�O)�L�a��"�R�ZƓ뙤'��v�iP�߳��R�=�IsQ���$�'�C���?jI;1<l��|�<����\      O   �   x��ϱ
�0����%��KҢ���]\��@RH���n���=�?|�<T�m?(�C�ƭ2������|�6{�e��@z���]�L��w��r��k$�/�F�аph���D��d9�����k�0衘?zc1��٣�����R)�[p��      K   j   x�3�4�4�tN,J�42�30 �Fƺ&�FƜn�E
�e�y%
!E��\F�F`�����ԂL�2*1�tJ��Ʈb�^*�	�)�	�jC���AT�4F��� 	�,�      N   #   x�ILW�(JM�
���K� �̲T�=... �,	�      G   �  x�͕�n�0E����m���n���Ht��T�m6)PRR�ȿw(��G�v����3CRW�`��/���>�R���\���;���y��=UvU��!������b��0)�-��1j��|�Y�C�Q�<k�2�!,�!����M����;Oa,�F%�*ԉD�@��œ�m�T�+DA�G�m�W_�N�Gas�왩7��<"pB�v�(�ߋ����贐ן����������/0�<ό-����~ЃN�żS���Sf�`�wn�i��Čv�WT�H�D�P)agz�5��o�B����/ E�)����0� �p����U�<�68'5*P��ʰ]�;^��Q'\z![!�l�P4���/}r�8����,���xy&b9�����������lJ�7+l!�R�����6�FՍ�ö,|F[�r}�����D�sIB��|ٙw�7�y�{.���;�j����y�x��w��}�טH�s ����n:�!�_A\�]A>ތC:W�|?�i�WXZ�!�b��q��o�j!\�`*ք���|OtM���Zu�~{k�Թ�o������Gz�r!��w+��nO%r7i���PudV��mU��R����f0��;�rN��2�BGh��ܔ�8oS� ��4�o7�?ur�C     