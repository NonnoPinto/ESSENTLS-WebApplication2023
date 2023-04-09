PGDMP                  
        {           ESSENTLS    10.23    15.2 ,    ~           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17194    ESSENTLS    DATABASE     �   CREATE DATABASE "ESSENTLS" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Malaysia.1252';
    DROP DATABASE "ESSENTLS";
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            �           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    8                        3079    17217    chkpass 	   EXTENSION     ;   CREATE EXTENSION IF NOT EXISTS chkpass WITH SCHEMA public;
    DROP EXTENSION chkpass;
                   false    8            �           0    0    EXTENSION chkpass    COMMENT     J   COMMENT ON EXTENSION chkpass IS 'data type for auto-encrypted passwords';
                        false    3                        3079    17227    citext 	   EXTENSION     :   CREATE EXTENSION IF NOT EXISTS citext WITH SCHEMA public;
    DROP EXTENSION citext;
                   false    8            �           0    0    EXTENSION citext    COMMENT     S   COMMENT ON EXTENSION citext IS 'data type for case-insensitive character strings';
                        false    2            ~           1247    17212    gen    TYPE     5   CREATE TYPE public.gen AS ENUM (
    'f',
    'm'
);
    DROP TYPE public.gen;
       public          postgres    false    8            �            1259    17428    Causes    TABLE     T   CREATE TABLE public."Causes" (
    id integer NOT NULL,
    name text[] NOT NULL
);
    DROP TABLE public."Causes";
       public            postgres    false    8            �            1259    17426    Causes_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Causes_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Causes_id_seq";
       public          postgres    false    205    8            �           0    0    Causes_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Causes_id_seq" OWNED BY public."Causes".id;
          public          postgres    false    204            �            1259    17354    Events    TABLE     m  CREATE TABLE public."Events" (
    id integer NOT NULL,
    name text NOT NULL,
    description text[] NOT NULL,
    price integer NOT NULL,
    visibility boolean DEFAULT false,
    location text,
    maxparticipantsinternational integer NOT NULL,
    maxparticipantsvolunteer integer NOT NULL,
    eventstart timestamp without time zone,
    eventend timestamp without time zone,
    subscriptionstart timestamp without time zone,
    subscriptionend timestamp without time zone,
    withdrawalend timestamp without time zone,
    maxwaitinglist integer,
    attributes text[],
    thumbnail text[],
    poster text
);
    DROP TABLE public."Events";
       public            postgres    false    8            �            1259    17352    Events_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Events_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Events_id_seq";
       public          postgres    false    8    199            �           0    0    Events_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Events_id_seq" OWNED BY public."Events".id;
          public          postgres    false    198            �            1259    17406    Payments    TABLE     �   CREATE TABLE public."Payments" (
    id integer NOT NULL,
    user_id integer,
    event_id integer,
    method text NOT NULL,
    amount integer NOT NULL,
    date date DEFAULT CURRENT_DATE NOT NULL,
    notes text[]
);
    DROP TABLE public."Payments";
       public            postgres    false    8            �            1259    17404    Payments_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Payments_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."Payments_id_seq";
       public          postgres    false    203    8            �           0    0    Payments_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public."Payments_id_seq" OWNED BY public."Payments".id;
          public          postgres    false    202            �            1259    17437    Tags    TABLE     .   CREATE TABLE public."Tags" (
    name text
);
    DROP TABLE public."Tags";
       public            postgres    false    8            �            1259    17366    Users    TABLE     �  CREATE TABLE public."Users" (
    id integer NOT NULL,
    email public.citext NOT NULL,
    password public.chkpass NOT NULL,
    cardid integer,
    tier integer,
    registrationdate date DEFAULT CURRENT_DATE NOT NULL,
    name character varying(50) NOT NULL,
    surname character varying(50) NOT NULL,
    sex public.gen,
    dob date,
    nationality character varying(50),
    homecountryaddress json,
    periodofstay character varying(50),
    phonenumber bigint,
    padovaaddress json,
    documenttype character varying(50),
    documentnumber character varying(50),
    documentfile character varying(50),
    diettype text[],
    allergies text[],
    emailhash text,
    emailconfirmed boolean DEFAULT false
);
    DROP TABLE public."Users";
       public            postgres    false    2    8    2    8    2    8    2    8    2    8    8    638    3    3    8    3    8    8            �            1259    17364    Users_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Users_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public."Users_id_seq";
       public          postgres    false    201    8            �           0    0    Users_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public."Users_id_seq" OWNED BY public."Users".id;
          public          postgres    false    200            �
           2604    17431 	   Causes id    DEFAULT     j   ALTER TABLE ONLY public."Causes" ALTER COLUMN id SET DEFAULT nextval('public."Causes_id_seq"'::regclass);
 :   ALTER TABLE public."Causes" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    205    204    205            �
           2604    17357 	   Events id    DEFAULT     j   ALTER TABLE ONLY public."Events" ALTER COLUMN id SET DEFAULT nextval('public."Events_id_seq"'::regclass);
 :   ALTER TABLE public."Events" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    199    198    199            �
           2604    17409    Payments id    DEFAULT     n   ALTER TABLE ONLY public."Payments" ALTER COLUMN id SET DEFAULT nextval('public."Payments_id_seq"'::regclass);
 <   ALTER TABLE public."Payments" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    203    203            �
           2604    17369    Users id    DEFAULT     h   ALTER TABLE ONLY public."Users" ALTER COLUMN id SET DEFAULT nextval('public."Users_id_seq"'::regclass);
 9   ALTER TABLE public."Users" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    201    200    201            z          0    17428    Causes 
   TABLE DATA           ,   COPY public."Causes" (id, name) FROM stdin;
    public          postgres    false    205    2       t          0    17354    Events 
   TABLE DATA             COPY public."Events" (id, name, description, price, visibility, location, maxparticipantsinternational, maxparticipantsvolunteer, eventstart, eventend, subscriptionstart, subscriptionend, withdrawalend, maxwaitinglist, attributes, thumbnail, poster) FROM stdin;
    public          postgres    false    199   =2       x          0    17406    Payments 
   TABLE DATA           X   COPY public."Payments" (id, user_id, event_id, method, amount, date, notes) FROM stdin;
    public          postgres    false    203   Z2       {          0    17437    Tags 
   TABLE DATA           &   COPY public."Tags" (name) FROM stdin;
    public          postgres    false    206   w2       v          0    17366    Users 
   TABLE DATA             COPY public."Users" (id, email, password, cardid, tier, registrationdate, name, surname, sex, dob, nationality, homecountryaddress, periodofstay, phonenumber, padovaaddress, documenttype, documentnumber, documentfile, diettype, allergies, emailhash, emailconfirmed) FROM stdin;
    public          postgres    false    201   �2       �           0    0    Causes_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public."Causes_id_seq"', 1, false);
          public          postgres    false    204            �           0    0    Events_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public."Events_id_seq"', 1, false);
          public          postgres    false    198            �           0    0    Payments_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public."Payments_id_seq"', 1, false);
          public          postgres    false    202            �           0    0    Users_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Users_id_seq"', 1, false);
          public          postgres    false    200            �
           2606    17436    Causes Causes_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Causes"
    ADD CONSTRAINT "Causes_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Causes" DROP CONSTRAINT "Causes_pkey";
       public            postgres    false    205            �
           2606    17363    Events Events_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "Events_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Events" DROP CONSTRAINT "Events_pkey";
       public            postgres    false    199            �
           2606    17415    Payments Payments_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT "Payments_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT "Payments_pkey";
       public            postgres    false    203            �
           2606    17378    Users Users_email_key 
   CONSTRAINT     U   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_email_key" UNIQUE (email);
 C   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_email_key";
       public            postgres    false    201            �
           2606    17376    Users Users_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY (id);
 >   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_pkey";
       public            postgres    false    201            �
           2606    17421    Payments fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES public."Events"(id) ON DELETE SET NULL;
 =   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_event;
       public          postgres    false    199    203    2799            �
           2606    17416    Payments fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public."Users"(id) ON DELETE SET NULL;
 <   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_user;
       public          postgres    false    203    2803    201            z      x������ � �      t      x������ � �      x      x������ � �      {      x������ � �      v      x������ � �     