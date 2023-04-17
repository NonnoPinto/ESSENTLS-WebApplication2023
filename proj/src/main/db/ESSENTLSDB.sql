PGDMP         
                {           postgres    15.2    15.2 /    7           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            8           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            9           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            :           1262    5    postgres    DATABASE     ~   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Malaysia.1252';
    DROP DATABASE postgres;
                postgres    false            ;           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    3386                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            <           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    2            O           1247    17581    gen    TYPE     K   CREATE TYPE public.gen AS ENUM (
    'female',
    'male',
    'others'
);
    DROP TYPE public.gen;
       public          postgres    false            �            1259    17634    Causes    TABLE     R   CREATE TABLE public."Causes" (
    id integer NOT NULL,
    name text NOT NULL
);
    DROP TABLE public."Causes";
       public         heap    postgres    false            �            1259    17633    Causes_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Causes_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Causes_id_seq";
       public          postgres    false    222            =           0    0    Causes_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Causes_id_seq" OWNED BY public."Causes".id;
          public          postgres    false    221            �            1259    17602    Events    TABLE     �  CREATE TABLE public."Events" (
    id integer NOT NULL,
    name text NOT NULL,
    description text,
    price numeric(10,2),
    visibility integer DEFAULT 3,
    location json,
    maxparticipantsinternational integer,
    maxparticipantsvolunteer integer,
    eventstart timestamp without time zone,
    eventend timestamp without time zone,
    subscriptionstart timestamp without time zone,
    subscriptionend timestamp without time zone,
    withdrawalend timestamp without time zone,
    maxwaitinglist integer,
    attributes text[],
    thumbnail text,
    poster text,
    CONSTRAINT "Events_price_check" CHECK ((price >= (0)::numeric))
);
    DROP TABLE public."Events";
       public         heap    postgres    false            �            1259    17601    Events_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Events_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Events_id_seq";
       public          postgres    false    218            >           0    0    Events_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Events_id_seq" OWNED BY public."Events".id;
          public          postgres    false    217            �            1259    17649    Participants    TABLE     �   CREATE TABLE public."Participants" (
    userid integer NOT NULL,
    eventid integer NOT NULL,
    role text,
    date timestamp without time zone,
    attributevalues json
);
 "   DROP TABLE public."Participants";
       public         heap    postgres    false            �            1259    17613    Payments    TABLE     1  CREATE TABLE public."Payments" (
    id integer NOT NULL,
    userid integer NOT NULL,
    eventid integer,
    method text NOT NULL,
    amount numeric(10,2) NOT NULL,
    date date DEFAULT CURRENT_DATE NOT NULL,
    notes text,
    CONSTRAINT "Payments_amount_check" CHECK ((amount >= (0)::numeric))
);
    DROP TABLE public."Payments";
       public         heap    postgres    false            �            1259    17612    Payments_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Payments_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."Payments_id_seq";
       public          postgres    false    220            ?           0    0    Payments_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public."Payments_id_seq" OWNED BY public."Payments".id;
          public          postgres    false    219            �            1259    17642    Tags    TABLE     7   CREATE TABLE public."Tags" (
    name text NOT NULL
);
    DROP TABLE public."Tags";
       public         heap    postgres    false            �            1259    17588    Users    TABLE       CREATE TABLE public."Users" (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    cardid character varying(50),
    tier integer DEFAULT 0,
    registrationdate date,
    name character varying(50),
    surname character varying(50),
    sex public.gen,
    dateofbirth date,
    nationality character varying(100),
    homecountryaddress json,
    homecountryuniversity character varying(150),
    periodofstay character varying(50),
    phonenumber character varying(50),
    paduaaddress json,
    documenttype character varying(50),
    documentnumber character varying(50),
    documentfile text,
    diettype text[],
    allergies text[],
    emailhash text,
    emailconfirmed boolean DEFAULT false,
    CONSTRAINT "Users_email_check" CHECK (((email)::text ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'::text))
);
    DROP TABLE public."Users";
       public         heap    postgres    false    847            �            1259    17587    Users_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Users_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public."Users_id_seq";
       public          postgres    false    216            @           0    0    Users_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public."Users_id_seq" OWNED BY public."Users".id;
          public          postgres    false    215            �           2604    17637 	   Causes id    DEFAULT     j   ALTER TABLE ONLY public."Causes" ALTER COLUMN id SET DEFAULT nextval('public."Causes_id_seq"'::regclass);
 :   ALTER TABLE public."Causes" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    17605 	   Events id    DEFAULT     j   ALTER TABLE ONLY public."Events" ALTER COLUMN id SET DEFAULT nextval('public."Events_id_seq"'::regclass);
 :   ALTER TABLE public."Events" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            �           2604    17616    Payments id    DEFAULT     n   ALTER TABLE ONLY public."Payments" ALTER COLUMN id SET DEFAULT nextval('public."Payments_id_seq"'::regclass);
 <   ALTER TABLE public."Payments" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220            �           2604    17591    Users id    DEFAULT     h   ALTER TABLE ONLY public."Users" ALTER COLUMN id SET DEFAULT nextval('public."Users_id_seq"'::regclass);
 9   ALTER TABLE public."Users" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            2          0    17634    Causes 
   TABLE DATA           ,   COPY public."Causes" (id, name) FROM stdin;
    public          postgres    false    222   68       .          0    17602    Events 
   TABLE DATA             COPY public."Events" (id, name, description, price, visibility, location, maxparticipantsinternational, maxparticipantsvolunteer, eventstart, eventend, subscriptionstart, subscriptionend, withdrawalend, maxwaitinglist, attributes, thumbnail, poster) FROM stdin;
    public          postgres    false    218   |8       4          0    17649    Participants 
   TABLE DATA           V   COPY public."Participants" (userid, eventid, role, date, attributevalues) FROM stdin;
    public          postgres    false    224   :       0          0    17613    Payments 
   TABLE DATA           V   COPY public."Payments" (id, userid, eventid, method, amount, date, notes) FROM stdin;
    public          postgres    false    220   �:       3          0    17642    Tags 
   TABLE DATA           &   COPY public."Tags" (name) FROM stdin;
    public          postgres    false    223   J;       ,          0    17588    Users 
   TABLE DATA           4  COPY public."Users" (id, email, password, cardid, tier, registrationdate, name, surname, sex, dateofbirth, nationality, homecountryaddress, homecountryuniversity, periodofstay, phonenumber, paduaaddress, documenttype, documentnumber, documentfile, diettype, allergies, emailhash, emailconfirmed) FROM stdin;
    public          postgres    false    216   g;       A           0    0    Causes_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Causes_id_seq"', 5, true);
          public          postgres    false    221            B           0    0    Events_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Events_id_seq"', 6, true);
          public          postgres    false    217            C           0    0    Payments_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public."Payments_id_seq"', 14, true);
          public          postgres    false    219            D           0    0    Users_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public."Users_id_seq"', 8, true);
          public          postgres    false    215            �           2606    17641    Causes Causes_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Causes"
    ADD CONSTRAINT "Causes_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Causes" DROP CONSTRAINT "Causes_pkey";
       public            postgres    false    222            �           2606    17611    Events Events_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "Events_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Events" DROP CONSTRAINT "Events_pkey";
       public            postgres    false    218            �           2606    17655    Participants Participants_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT "Participants_pkey" PRIMARY KEY (userid, eventid);
 L   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT "Participants_pkey";
       public            postgres    false    224    224            �           2606    17622    Payments Payments_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT "Payments_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT "Payments_pkey";
       public            postgres    false    220            �           2606    17648    Tags Tags_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Tags"
    ADD CONSTRAINT "Tags_pkey" PRIMARY KEY (name);
 <   ALTER TABLE ONLY public."Tags" DROP CONSTRAINT "Tags_pkey";
       public            postgres    false    223            �           2606    17600    Users Users_email_key 
   CONSTRAINT     U   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_email_key" UNIQUE (email);
 C   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_email_key";
       public            postgres    false    216            �           2606    17598    Users Users_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY (id);
 >   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_pkey";
       public            postgres    false    216            �           2606    17628    Payments fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_event FOREIGN KEY (eventid) REFERENCES public."Events"(id) ON DELETE SET NULL;
 =   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_event;
       public          postgres    false    218    220    3216            �           2606    17661    Participants fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT fk_event FOREIGN KEY (eventid) REFERENCES public."Events"(id) ON DELETE SET NULL;
 A   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT fk_event;
       public          postgres    false    3216    218    224            �           2606    17623    Payments fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_user FOREIGN KEY (userid) REFERENCES public."Users"(id) ON DELETE SET NULL;
 <   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_user;
       public          postgres    false    220    3214    216            �           2606    17656    Participants fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT fk_user FOREIGN KEY (userid) REFERENCES public."Users"(id) ON DELETE SET NULL;
 @   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT fk_user;
       public          postgres    false    224    216    3214            2   6   x�3�tN,-NU��K�2��C��a쌢�T.(�-������,K����� ȯ�      .   �  x�͔MO�0���S�c���'��-��tZ��A�0��[��ha��f�?ڇ_�O��x������Z��^gB������D���~0j�z���sU�'��9˕,�/@!@1!��G0!0�6�g�B�C<��#���f�~�Q�T!�;�K_�<����X�u�obÓ(�fI��E�V���z�-s&6�����b+Kŋ��GN�	�Ś�^�){�k�<[�V�	��$D��xd��HB8��i�a��n��1>y\�:&���n�u��s~��{^j�]��`U�+�?>!��D�R�gb���nV�'>�����N�t2{l�i�Q=��&��	7�_$�B�Q�x|=��Ƥ��`�;�w�ਔY�|��\��l�z"9v��񣖴��q�]�����o��`      4   �   x��ѽ
�0����*Bf#'�ђp;���@6�4� ޻-
������S`��@�6����t�pM�ɷ�L[��,�@b�?�@���Է�S�ާW��x���]�9��)�����`GCK齯��i��>#�ܧ&��>���Ű�1�Y���      0   r   x�u�1
�0D�z�^ ���؋��X�(H�(�߈��6S}�C`��e��S��(Δ�h֐ԇ�{�O�9Aۢ�	�W]a$�(����ݭv>��e>��?�E�-��mJD'|3      3      x������ � �      ,   �  x���Mo�0���WD�K�ɞ6��
m�"hW�U/��$V9N[Z���$iՖS���qއ���P��Q��2ٮ�i;=b۴��eu��7�Q�"	pJ,�s;��A2���ܞ)y�D@�ߌ�*�����1�.$�ATZQ�7c&�/�Z�{�R�jȥ1�P��)��8=�v,��l�,�}�$��d.�4�e���B*4��vW�㮖ݵ�T7[q	a�ep�
���7�b�`E3�c��������2{Q�+�X����/���6|Ω�ָm&�Q���ʢb�h�o�\�e�O �4�"ːv�y�D��rR�(�����e6�������������Ub�Duf�[̲D�=�:�ft5�9����[���c��A��������@k�E���0�:���u���6g�lb��:�@eڄʮW��T>g9'tT	�L
�zGk�:�Zx�S̢�XB#.� ��Z�V�LDJ]�1$�D�(�ج�^7xUA��PYp�2��r"��4f���Lk���ʞ�b�pwO����8S4��	��lϲ=���>M��RQ%1��l���cWP����?)>��|�Xr�n�s�d
J�6k2��[P�\��dIK���4�!��s��tJ��i��<����}��Ơ�t���`�6@�|s�!7���2���Ґ!f�F�����Ǉ��i���w���     